package content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.circuit_board
import robogaggiamultiplatform.composeapp.generated.resources.dark_circuitboard
import robogaggiamultiplatform.composeapp.generated.resources.night
import theme.Typography

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ScreenContent(
    onFirstButtonClick: (() -> Unit)? = null,
    onSecondButtonClick: (() -> Unit)? = null,
    body1Resource: StringResource? = null,
    body2Resource: StringResource? = null,
    button1Resource: StringResource? = null,
    button2Resource: StringResource? = null,
    backgroundImage: DrawableResource = Res.drawable.dark_circuitboard,
    backgroundColor: Color? = null,
    content: (@Composable ColumnScope.() -> Unit)? = null
) =
    Box(modifier = Modifier.fillMaxSize()
        .paint(painterResource(backgroundImage),
              contentScale = ContentScale.FillBounds)
        .background(color = backgroundColor ?: Color.Transparent)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxHeight().weight(.20F).padding(horizontal = 10.dp)
            ) {
                button2Resource?.let {
                    OutlinedButton(
                        onClick = { onSecondButtonClick?.invoke() },
                        modifier = Modifier.requiredSize(150.dp).weight(.5f).padding(20.dp),
                        shape = CircleShape,
                        border = BorderStroke(5.dp, Color(0XFF0F9D58)),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = androidx.compose.ui.graphics.Color.White)
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
                        Text(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            style = Typography.body2,
                            textAlign = TextAlign.Center,
                            text = stringResource(it)
                        )
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
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = androidx.compose.ui.graphics.Color.White)
                    ) {
                        Text(text = stringResource(button1Resource))
                    }
                }
            }
        }
    }
