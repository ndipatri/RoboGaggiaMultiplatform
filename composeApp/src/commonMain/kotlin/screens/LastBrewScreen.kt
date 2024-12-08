package screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.datastore.core.DataStore
import content.BrewChartContent
import content.ScreenContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import robo.ndipatri.robogaggia.proto_datastore_kmm.TelemetryProtoData
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.acknowledged
import robogaggiamultiplatform.composeapp.generated.resources.click_to_acknowledge
import robogaggiamultiplatform.composeapp.generated.resources.done
import robogaggiamultiplatform.composeapp.generated.resources.last_brew
import robogaggiamultiplatform.composeapp.generated.resources.last_brew_no_data
import vms.Telemetry

@Composable
fun LastBrewScreen(
    telemetry: Telemetry?,
    onDoneClicked: () -> Unit,
) {
    if (telemetry?.telemetry?.isNotEmpty() == true) {
        BrewChartContent(telemetry = telemetry) {

            KoinContext {
                val telemetryDataStore = koinInject<DataStore<TelemetryProtoData>>()
                val changedTelemetryFlow =
                    telemetryDataStore.data as Flow<TelemetryProtoData>
                val acknowledgedTelemetry by changedTelemetryFlow.map { it.acknowledged }
                    .collectAsState(initial = false)

                val coroutineScope = rememberCoroutineScope()

                ScreenContent(
                    body1Resource = Res.string.last_brew,
                    button1Resource = Res.string.done,
                    button4Resource =
                    if (acknowledgedTelemetry)
                        Res.string.acknowledged
                    else
                        Res.string.click_to_acknowledge,
                    onFourthButtonClick = {
                        coroutineScope.launch {
                            telemetryDataStore.updateData { storedTelemetry ->
                                // Preserve our current brew telemetry to be used later for review!
                                storedTelemetry.copy(
                                    acknowledged = !acknowledgedTelemetry
                                )
                            }
                        }
                    },
                    telemetry = telemetry,
                    showFireContent = false,
                    onFirstButtonClick = onDoneClicked,
                    backgroundImage = null,
                )
            }
        }
    } else {
        ScreenContent(
            body1Resource = Res.string.last_brew_no_data,
            button1Resource = Res.string.done,
            button2Resource = null,
            telemetry = null,
            onFirstButtonClick = onDoneClicked,
        )
    }
}

