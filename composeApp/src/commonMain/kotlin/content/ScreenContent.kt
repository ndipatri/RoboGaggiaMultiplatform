package content

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.dark_circuitboard
import theme.Typography

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ScreenContent(
    onFirstButtonClick: (() -> Unit)? = null,
    onSecondButtonClick: (() -> Unit)? = null,
    body1Resource: StringResource? = null,
    body2Resource: StringResource? = null,
    userMessage: String? = null,
    button1Resource: StringResource? = null,
    button2Resource: StringResource? = null,
    backgroundImage: DrawableResource? = Res.drawable.dark_circuitboard,
    backgroundColor: Color? = null,
    shouldUIDisappear: Boolean = false,
    content: (@Composable ColumnScope.() -> Unit)? = null
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = backgroundColor ?: Color.Transparent).then(
                if (backgroundImage != null) {
                    Modifier.paint(
                        painterResource(backgroundImage),
                        contentScale = ContentScale.FillBounds
                    )
                } else {
                    Modifier
                }
            )
    ) {

        var shouldBeVisible by remember { mutableStateOf(true) }

        val alpha: Float by animateFloatAsState(
            targetValue = if (shouldBeVisible) 1f else 0f,
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )

        if (shouldUIDisappear) {
            LaunchedEffect(shouldBeVisible) {
                delay(4000)

                // after 2 seconds, the buttons disappear again...
                shouldBeVisible = false
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer(alpha = alpha)
                .clickable { if (shouldUIDisappear) shouldBeVisible = !shouldBeVisible }) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(.20F)
                    .padding(horizontal = 10.dp)
            ) {
                button2Resource?.let {
                    OutlinedButton(
                        onClick = { onSecondButtonClick?.invoke() },
                        modifier = Modifier.requiredSize(150.dp).weight(.5f).padding(20.dp),
                        shape = CircleShape,
                        border = BorderStroke(5.dp, Color(0XFF0F9D58)),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = androidx.compose.ui.graphics.Color.White,
                            backgroundColor = androidx.compose.ui.graphics.Color.Black
                        )
                    ) {
                        Text(text = stringResource(button2Resource))
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxSize().weight(.60F)
            ) {
                Row(modifier = Modifier.weight(.30f).align(Alignment.CenterHorizontally)) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = Typography.body1,
                        textAlign = TextAlign.Center,
                        text = body1Resource?.let { stringResource(body1Resource) } ?: ""
                    )
                }

                Row(modifier = Modifier.weight(.40f).align(Alignment.CenterHorizontally)) {
                    body2Resource?.let {
                        RotatingMessageTextBox(stringResource(body2Resource), userMessage)
                    }
                }

                Row(modifier = Modifier.weight(.30f).align(Alignment.CenterHorizontally)) {
                    content?.invoke(this@Column)
                }
            }

            Column(
                modifier = Modifier.fillMaxHeight().weight(.20F)
            ) {
                button1Resource?.let {
                    OutlinedButton(
                        onClick = { onFirstButtonClick?.invoke() },
                        modifier = Modifier.requiredSize(150.dp).weight(.5f).padding(20.dp),
                        shape = CircleShape,
                        border = BorderStroke(5.dp, Color(0XFF0F9D58)),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = androidx.compose.ui.graphics.Color.White,
                            backgroundColor = androidx.compose.ui.graphics.Color.Black
                        )
                    ) {
                        Text(text = stringResource(button1Resource))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RowScope.RotatingMessageTextBox(message1: String, message2: String?) {

    var showMessage1 by remember { mutableStateOf(true) }
    var fadeOut by remember { mutableStateOf(false) }

    val alpha: Float by animateFloatAsState(
        targetValue = if (fadeOut) 0f else 1f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(message2 != null) {
        while(message2 != null) {
            // message is currently being shown
            delay(500)

            // start the animation to fade out message
            fadeOut = true

            // wait for animation to finish
            delay(500)

            // switch to other message and fade it back in
            showMessage1 = !showMessage1
            fadeOut = false

            // wait for animation to finish
            delay(500)

            // now next message is being shown
        }
    }

    Text(
        modifier = Modifier.align(Alignment.CenterVertically)
            .graphicsLayer(alpha = alpha),
        style = Typography.body2,
        textAlign = TextAlign.Center,
        text = if (showMessage1) message1 else message2!!
    )
}
