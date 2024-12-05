package content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import robo.ndipatri.robogaggia.proto_datastore_kmm.TelemetryProtoData
import screens.DelayedClickButton
import theme.Purple40
import theme.PurpleGrey40_50
import theme.PurpleGrey80
import theme.RoboGaggiaTheme
import vms.GaggiaState
import vms.Telemetry
import vms.TelemetryMessage
import vms.Weight

@Composable
// This composable assumes the UIState telemetry includes preinfusion
// and brewing data.
fun BrewChartContent(telemetry: Telemetry, content: (@Composable () -> Unit)? = null) {
    RoboGaggiaTheme {
        Surface(
            // uses theme
            modifier = Modifier.fillMaxSize(),
            color = Color.Black,
        ) {
            KoinContext {
                val telemetryDataStore = koinInject<DataStore<TelemetryProtoData>>()

                val (seriesList, maxValueList, unitList, colorList) = SeriesData(
                    telemetry.telemetry
                )

                var preinfusionTimeSeconds by rememberSaveable { mutableStateOf(0) }
                var brewTimeSeconds by rememberSaveable { mutableStateOf(0) }
                var timeString by rememberSaveable { mutableStateOf("") }

                val visibleSeriesMap = remember {
                    mutableStateMapOf(
                        0 to true,
                        1 to true,
                        2 to true,
                        3 to true,
                        4 to true
                    )
                }

                val xStepsPerScreen = 40
                val secondsPerStep = 1.2

                var telemetrySaved by remember { mutableStateOf(false) }

                // Track preinfusion and brew time for shot clock overlay
                LaunchedEffect(telemetry.currentState) {
                    while (true) {
                        delay(1000)
                        when (telemetry.currentState) {
                            GaggiaState.PREINFUSION -> {
                                preinfusionTimeSeconds += 1
                            }

                            GaggiaState.BREWING -> {
                                brewTimeSeconds += 1
                            }

                            GaggiaState.DONE_BREWING -> {
                                if (!telemetrySaved) {
                                    telemetrySaved = true
                                    telemetryDataStore.updateData { storedTelemetry ->
                                        // Preserve our current brew telemetry to be used later for review!
                                        storedTelemetry.copy(telemetry = telemetry.telemetry.map { it.toTelemetryMessageProto() })
                                    }
                                }
                            }

                            else -> {}
                        }

                        timeString = buildString {
                            when (telemetry.currentState) {
                                GaggiaState.PREINFUSION -> {
                                    append("Preinfusion (${preinfusionTimeSeconds}) sec")
                                }

                                GaggiaState.BREWING, GaggiaState.DONE_BREWING -> {
                                    append("Brew (${preinfusionTimeSeconds}, ${brewTimeSeconds}) sec")
                                }

                                else -> {}
                            }
                        }
                    }
                }

                val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(lifecycleOwner) {
                    onDispose {
                        if (telemetry.currentState == GaggiaState.DONE_BREWING) {
                            // Now that we've used this data, we have to clear it out as this
                            // screen is stateful...
                            preinfusionTimeSeconds = 0
                            brewTimeSeconds = 0
                            timeString = ""
                        }
                    }
                }

                Box(
                    modifier = Modifier.padding(
                        start = 10.dp,
                        top = 10.dp,
                        end = 10.dp,
                        bottom = 30.dp
                    )
                ) {
                    val numberOfVisibleRows = visibleSeriesMap.filterValues { value -> value }.size

                    GridBackground(
                        numberOfColumns = numberOfVisibleRows + 2,
                        numberOfRows = numberOfVisibleRows
                    )

                    // Shot clock overlay
                    Text(
                        modifier = Modifier.align(Alignment.Center).graphicsLayer(alpha = .5F),
                        text = timeString,
                        color = Color.White,
                        fontSize = 48.sp
                    )
                    Legend(
                        modifier = Modifier.padding(start = 30.dp),
                        seriesList = seriesList,
                        visibleSeriesMap = visibleSeriesMap,
                        onSeriesSelected = {
                            visibleSeriesMap[it] = visibleSeriesMap[it]?.not() ?: true
                        },
                        unitList = unitList,
                        colorList = colorList,
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        seriesList.forEachIndexed() { index, series ->
                            if (visibleSeriesMap[index] != false) {
                                LineGraph(
                                    modifier = Modifier.weight(1f),
                                    pathColor = colorList[index],
                                    yValues = series,
                                    yMaxValue = maxValueList[index],
                                    xStepsPerScreen = xStepsPerScreen
                                )
                            }
                        }
                    }

                    content?.invoke()
                }

                // NJD TODO - The TimeBar uses native canvas operations which would require the
                // expected/actual mechanism which i'm not ready to do for this yes.
                //TimeBar(xStepsPerScreen, seriesList[0].size, secondsPerStep)
            }
        }
    }
}

@Composable
private fun TimeBar(
    xStepsPerScreen: Int,
    numberOfStepsToDisplay: Int,
    secondsPerStep: Double
) {
    val screenDensity = LocalDensity.current
    val textPaint = remember(screenDensity) {
//        Paint().apply {
//            TextForegroundStyle.Unspecified.color = Color.White.toArgb()
//            textAlign = Paint.Align.CENTER
//            textSize = screenDensity.run { 12.sp.toPx() }
//        }
    }

    Canvas(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxSize()
    ) {

        val xPxPerStep = size.width / xStepsPerScreen
        for (index in 1..numberOfStepsToDisplay) {
            // We only draw text at certain time intervals
            if (index % 5 == 0 || index == 1 || index == numberOfStepsToDisplay) {

//                drawContext.canvas.nativeCanvas.drawText(
//                    "${(index * secondsPerStep).toInt()}s",
//                    size.width - (xPxPerStep * (numberOfStepsToDisplay - index) + 35),
//                    size.height,
//                    textPaint
//                )
            }
        }
    }
}

@Composable
private fun GridBackground(
    numberOfColumns: Int,
    numberOfRows: Int
) {
    // we cache this so the only time it gets recomposed is if
    // the size of the drawing area changes
    Spacer(modifier = Modifier
        .fillMaxSize()
        .drawWithCache {
            onDrawBehind {
                val borderWidth = 2.dp.toPx()

                // vertical axis
                drawLine(
                    PurpleGrey80,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = borderWidth
                )

                // vertical ticks
                val verticalTickSpacing = 10.dp.toPx()
                repeat((size.height / verticalTickSpacing).toInt()) { tickIndex ->
                    drawLine(
                        PurpleGrey80,
                        start = Offset(0f, tickIndex * verticalTickSpacing),
                        end = Offset(5.dp.toPx(), tickIndex * verticalTickSpacing),
                        strokeWidth = borderWidth
                    )
                }

                // horizontal axis
                drawLine(
                    PurpleGrey80,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderWidth
                )

                // horizontal ticks
                val horizontalTickSpacing = 10.dp.toPx()
                repeat((size.width / horizontalTickSpacing).toInt()) { tickIndex ->
                    drawLine(
                        PurpleGrey80,
                        start = Offset(tickIndex * horizontalTickSpacing, size.height),
                        end = Offset(tickIndex * horizontalTickSpacing, size.height - 10f),
                        strokeWidth = borderWidth
                    )
                }

                // vertical lines
                repeat(numberOfColumns - 1) { columnIndex -> // vertical lines
                    val startX = (size.width / numberOfColumns) * (columnIndex + 1)
                    drawLine(
                        PurpleGrey40_50,
                        start = Offset(startX, 0f),
                        end = Offset(startX, size.height),
                        strokeWidth = borderWidth
                    )
                }

                // horizontal lines
                repeat(numberOfRows - 1) { columnIndex -> // vertical lines
                    val startY = (size.height / numberOfRows) * (columnIndex + 1)
                    drawLine(
                        PurpleGrey40_50,
                        start = Offset(0f, startY),
                        end = Offset(size.width, startY),
                        strokeWidth = borderWidth
                    )
                }
            }
        }
    )
}

@Composable
private fun Legend(
    modifier: Modifier,
    seriesList: List<List<Any>>,
    visibleSeriesMap: Map<Int, Boolean>,
    onSeriesSelected: (Int) -> Unit,
    unitList: List<String>,
    colorList: List<Color>,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        Column(modifier = Modifier.fillMaxHeight()) {
            // Single head row with all series not currently being displayed
            Row(
                modifier = Modifier.border(
                    BorderStroke(1.dp, PurpleGrey80),
                    shape = RoundedCornerShape(20.dp)
                )
            ) {
                seriesList.forEachIndexed { index, series ->
                    if (visibleSeriesMap[index] == false) {
                        DelayedClickButton(onClick = { onSeriesSelected(index) }) {
                            Text(
                                modifier = Modifier
                                    .padding(end = 20.dp),
                                text = unitList[index],
                                color = colorList[index],
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }

            // Row for each series being displayed
            seriesList.forEachIndexed { index, series ->

                if (visibleSeriesMap[index] != false) {
                    SeriesTitleRow(unitList[index],
                        colorList[index],
                        series.toSeriesDataValues().last(),
                        series.toSeriesTargetValues().last(),
                        onClick = { onSeriesSelected(index) })
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.SeriesTitleRow(
    seriesTitle: String,
    seriesColor: Color,
    seriesCurrentValue: Float,
    seriesTargetValue: Float?,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp)
            .weight(1f), // since this is a 'scoped' modifier, we need to extend ColumnScope for this Composable
        verticalAlignment = Alignment.CenterVertically
    ) {
        DelayedClickButton(
            onClick = onClick,
        ) {
            Text(
                text = seriesTitle,
                color = seriesColor,
                fontSize = 22.sp
            )
            Text(
                text = " ($seriesCurrentValue${seriesTargetValue?.let { "/ $it" } ?: ""})",
                color = seriesColor,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun LineGraph(
    modifier: Modifier,
    pathColor: Color,
    yValues: List<Any>,
    yMaxValue: Float,
    xStepsPerScreen: Int,
) {
    // This whole graph is just about rendering a Path...
    val path = Path()
    Canvas(
        // We need fillMaxSize.. otherwise, we are defining our size internally here.
        modifier = modifier.fillMaxSize()//.border(width = 1.dp, color = PurpleGrey80)
    ) {
        val xStepPx = size.width / xStepsPerScreen
        val yZeroPx = size.height // y value representing the x-axis of the graph

        // We divide up our available plot height based on the highest possible value that we would
        // need to draw.. This is so we don't exceed our bounds when drawing...
        val yScaleFactor = yZeroPx / yMaxValue

        // so we always start at 0 when drawing our graph
        val values = mutableListOf<Float>().apply {
            add(0F)
            addAll(yValues.toSeriesDataValues())
        }

        var previousXPosition = 0F
        var previousYPosition = 0F
        values.forEachIndexed { index, value ->
            // We start creating our path far enough to the
            // left so all values will fit.

            // NJD - this approach we create two fake control points in between data points..
            // We start creating our path far enough to the
            // left so all values will fit.
            val xPosition = size.width - (xStepPx * (values.size - index))
            var yPosition = 0F

            if (index == 0) {
                yPosition = yZeroPx

                // In order to draw a line to first point, we have to start
                // at origin for this graph
                path.moveTo(xPosition, yPosition)
            } else {
                yPosition = yZeroPx - (value * yScaleFactor)

                // We create two control points so the path between last point and
                // this point is not a straight one:
                //                      *   *
                //                cp2         p2
                //                 *
                //    p1          cp1
                //        *   *
                path.cubicTo(
                    (xPosition + previousXPosition) / 2,
                    previousYPosition,
                    (xPosition + previousXPosition) / 2,
                    yPosition,
                    xPosition,
                    yPosition
                )
            }

            previousXPosition = xPosition
            previousYPosition = yPosition

            // NJD TODO getting rid of dots for now...
            // we want an explicit mark at every data point
//            drawCircle(
//                color = pathColor,
//                radius = 10f,
//                center = Offset(xPosition, yPosition)
//            )

            drawPath(
                path = path,
                color = pathColor,
                style = Stroke(2.dp.toPx())
            )
        }
    }
}

data class SeriesData constructor(
    val seriesList: List<List<Any>>,
    val maxValueList: List<Float>,
    val unitList: List<String>,
    val colorList: List<Color>,
    val weightIndex: Int
) {
    constructor(accumulatedTelemetry: List<TelemetryMessage>) :
            this(
                seriesList = listOf(
                    accumulatedTelemetry.filter {
                        it.state in setOf(
                            GaggiaState.PREINFUSION,
                            GaggiaState.BREWING
                        )
                    }.map { it.weight },
                    accumulatedTelemetry.filter {
                        it.state in setOf(
                            GaggiaState.PREINFUSION,
                            GaggiaState.BREWING
                        )
                    }.map { it.pressureBars.toFloat() },
                    accumulatedTelemetry.filter {
                        it.state in setOf(
                            GaggiaState.PREINFUSION,
                            GaggiaState.BREWING
                        )
                    }.map { it.flowRateGPS.toFloat() },
                    accumulatedTelemetry.filter {
                        it.state in setOf(
                            GaggiaState.PREINFUSION,
                            GaggiaState.BREWING
                        )
                    }.map { it.brewTempC.currentTemp },
                    accumulatedTelemetry.filter {
                        it.state in setOf(
                            GaggiaState.PREINFUSION,
                            GaggiaState.BREWING
                        )
                    }.map { it.dutyCyclePercent.toFloat() }),

                maxValueList = listOf(50f, 15f, 5f, 150f, 100f),
                unitList = listOf("grams", "bars", "grams/sec", "tempC", "pumpPower"),
                colorList = listOf(Color.Yellow, Color.Red, Color.Magenta, Color.Green, Purple40),
                weightIndex = 0 // index in series where weight values reside
            )

}

fun List<Any>.toSeriesDataValues(): List<Float> =
    map {
        when (it) {
            is Weight -> it.currentWeight
            else -> it as Float
        }
    }

// Value is null if this series does NOT contain target values.
fun List<Any>.toSeriesTargetValues(): List<Float?> =
    map {
        when (it) {
            is Weight -> it.targetWeight
            else -> null
        }
    }
