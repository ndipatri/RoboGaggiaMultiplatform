package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BackflushDoneScreen(onDoneClicked: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.done_backflushing,
        body2Resource = Res.string.install_normal_basket,
        button1Resource = Res.string.done,
        onFirstButtonClick = onDoneClicked,
    )
}