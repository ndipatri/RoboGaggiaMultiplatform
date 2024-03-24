package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CleanGroupReadyScreen(onFirstButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.ready_to_clean_group,
        body2Resource = Res.string.hold_towel_under_group,
        button1Resource = Res.string.ready,
        onFirstButtonClick = onFirstButtonClick,
    )
}