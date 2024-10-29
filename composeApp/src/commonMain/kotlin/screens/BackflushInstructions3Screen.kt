package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BackflushInsructions3Screen(onReadyClicked: () -> Unit, onExitClicked: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.prepare_to_backflush_water,
        body2Resource = Res.string.rinse_out_backflush_portafilter,
        button1Resource = Res.string.ready,
        button2Resource = Res.string.exit,
        onFirstButtonClick = onReadyClicked,
        onSecondButtonClick = onExitClicked,
    )
}