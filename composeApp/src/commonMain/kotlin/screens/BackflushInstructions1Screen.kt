package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BackflushInsructions1Screen(onReadyClicked: () -> Unit, onExitClicked: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.prepare_to_backflush_cleaner,
        body2Resource = Res.string.install_backflush_basket,
        button1Resource = Res.string.ready,
        button2Resource = Res.string.exit,
        onFirstButtonClick = onReadyClicked,
        onSecondButtonClick = onExitClicked
    )
}