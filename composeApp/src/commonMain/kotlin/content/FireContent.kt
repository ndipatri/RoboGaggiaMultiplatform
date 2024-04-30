package content

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlinx.coroutines.delay
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.random.Random

/**
 *  Draws a rectangle of fire elements, each varying in color from black
 *  to bright orange.. thus simulating the look of an active fire.
 */
@Composable
fun FireContent(
    modifier: Modifier,
) {
    // The fire is an array of elements. Each element is expressed as an integer which
    // is an index into an array of color values.
    //
    // Physically, the fire is drawn as a rectangle of these elements.  The first element is the
    // upper left corner of the rectangle, and the last element is the lower right corner.
    var fireElements: MutableList<Int> by remember { mutableStateOf(mutableListOf()) }
    var fireDimensions: FireDimensions? by remember { mutableStateOf(null) }

    Canvas(modifier = modifier) {
        fireDimensions = FireDimensions(
            size.width.toInt(),
            size.height.toInt()
        )

        if (fireElements.isNotEmpty()) {
            fireDimensions?.let {
                drawFire(
                    fireElements,
                    it
                )
            }
        }
    }

    LaunchedEffect(fireDimensions) {

        fireDimensions?.let { fireDimensions ->

            // start with black fire with single row of bright white at the bottom
            fireElements = MutableList(fireDimensions.numberOfFireElements) { 0 }
                .apply { makeBottomRowOfFireWhiteHot(this, fireDimensions) }

            // now periodically shift the fire elements up and to the right/center/left
            // depending on wind direction
            while (true) {
                val newFireElements = fireElements.toMutableList()
                updateFireElements(newFireElements, fireDimensions)

                delay(100)

                fireElements = newFireElements
            }
        }
    }
}


/**
 * This draws the given fireElements on the canvas
 */
private fun DrawScope.drawFire(
    fireElements: List<Int>,
    fireDimension: FireDimensions
) {
    val widthInElements = fireDimension.fireWidthInElements
    val heightInElements = fireDimension.fireHeightInElements
    val elementSizePX = fireDimension.fireElementSizePX

    for (column in 0 until widthInElements) {
        for (row in 0 until heightInElements - 1) {
            val currentElementIndex = column + (widthInElements * row)
            val currentElementValue = fireElements[currentElementIndex]

            val elementWidthPX = ((column + 1) * elementSizePX).toFloat() -
                    (column * elementSizePX).toFloat()

            val elementHeightPX = ((row + 1) * elementSizePX).toFloat() -
                    (row * elementSizePX).toFloat()

            // Draw each fire element as a rectangle ...
            drawRect(
                // remember each element is an index into the fireColors array
                color = fireColors[currentElementValue],

                topLeft = Offset(
                    (column * elementSizePX).toFloat(),
                    (row * elementSizePX).toFloat()
                ),

                size = Size(width = elementWidthPX, height = elementHeightPX)
            )
        }
    }
}

private fun makeBottomRowOfFireWhiteHot(fireElements: MutableList<Int>, canvas: FireDimensions) {
    val overFlowFireIndex = canvas.fireWidthInElements * canvas.fireHeightInElements
    val elementIndexOfFirstElementInBottomRowOfFire = overFlowFireIndex - canvas.fireWidthInElements
    val brightestColorIndex = fireColors.size - 1

    for (column in 0 until canvas.fireWidthInElements) {
        val elementIndex = elementIndexOfFirstElementInBottomRowOfFire + column
        fireElements[elementIndex] = brightestColorIndex
    }
}

private fun updateFireElements(
    fireElements: MutableList<Int>,
    fireDimensions: FireDimensions,
    windDirection: WindDirection = WindDirection.Right
) {
    for (column in 0 .. fireDimensions.fireWidthInElements) {
        for (row in 1 .. fireDimensions.fireHeightInElements-2) {
            val currentElementIndex = column + (fireDimensions.fireWidthInElements * row)

            val belowElementIndex = currentElementIndex + fireDimensions.fireWidthInElements

            // if we are at the bottom-right-most element the fire, we can't go any further
            if (belowElementIndex >= fireDimensions.fireWidthInElements * fireDimensions.fireHeightInElements) {
                return
            }

            val offset = if (fireDimensions.tallerThanWide) 2 else 3
            val decay = floor(Random.nextDouble() * offset).toInt()

            val belowElementFireIntensity = fireElements[belowElementIndex]

            val newFireIntensity = when {
                belowElementFireIntensity - decay >= 0 -> belowElementFireIntensity - decay
                else -> 0
            }

            val newPosition = when (windDirection) {
                WindDirection.Right -> if (currentElementIndex - decay >= 0) currentElementIndex - decay else currentElementIndex
                WindDirection.Left -> if (currentElementIndex + decay >= 0) currentElementIndex + decay else currentElementIndex
                WindDirection.None -> currentElementIndex
            }

            fireElements[newPosition] = newFireIntensity
        }
    }
}


data class FireDimensions(
    val widthPX: Int,
    val heightPX: Int,
    val numberOfElementsInShortestDimension: Int = 50
)

val FireDimensions.tallerThanWide: Boolean
    get() = widthPX < heightPX

val FireDimensions.numberOfFireElements: Int
    get() = fireWidthInElements * fireHeightInElements

val FireDimensions.fireElementSizePX: Int
    get() {
        val shortestDimensionPX = if (tallerThanWide) widthPX else heightPX
        return ceil(shortestDimensionPX.toDouble() / numberOfElementsInShortestDimension).toInt()
    }

val FireDimensions.fireWidthInElements: Int
    get() = when {
        tallerThanWide -> numberOfElementsInShortestDimension
        else -> ceil(widthPX.toDouble() / fireElementSizePX).toInt()
    }

val FireDimensions.fireHeightInElements: Int
    get() = when {
        !tallerThanWide -> numberOfElementsInShortestDimension
        else -> ceil(heightPX.toDouble() / fireElementSizePX).toInt()
    }

sealed class WindDirection {
    object Right : WindDirection()
    object Left : WindDirection()
    object None : WindDirection()
}


val fireColors = arrayOf(
    //Color(red = 7, green = 7, blue = 7, alpha = 1),
    //Color(red = 31, green = 7, blue = 7, alpha = 1),
    Color(red = 7, green = 7, blue = 7, alpha = 0),
    Color(red = 31, green = 7, blue = 7, alpha = 0),
    Color(47, 15, 7),
    Color(71, 15, 7),
    Color(87, 23, 7),
    Color(103, 31, 7),
    Color(119, 31, 7),
    Color(143, 39, 7),
    Color(159, 47, 7),
    Color(175, 63, 7),
    Color(191, 71, 7),
    Color(199, 71, 7),
    Color(223, 79, 7),
    Color(223, 87, 7),
    Color(223, 87, 7),
    Color(215, 95, 7),
    Color(215, 95, 7),
    Color(215, 95, 7),
    Color(215, 103, 15),
    Color(207, 111, 15),
    Color(207, 119, 15),
    Color(207, 127, 15),
    Color(207, 135, 23),
    Color(199, 135, 23),
    Color(199, 143, 23),
    Color(199, 151, 31),
    Color(191, 159, 31),
    Color(191, 159, 31),
    Color(191, 167, 39),
    Color(191, 167, 39),
    Color(191, 175, 47),
    Color(183, 175, 47),
    Color(183, 183, 47),
    Color(183, 183, 55),
    Color(207, 207, 111),
    Color(223, 223, 159),
    Color(239, 239, 199),
    Color(255, 255, 255)
)
