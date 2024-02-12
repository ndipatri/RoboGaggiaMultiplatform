package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BackflushInsructions3Screen(onReadyClick: () -> Unit, onExitClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.backflush_inst3_primary,
        body2Resource = Res.string.backflush_inst3_subtitle,
        button1Resource = Res.string.ready,
        button2Resource = Res.string.exit,
        onFirstButtonClick = onReadyClick,
        onSecondButtonClick = onExitClick,
    )
}