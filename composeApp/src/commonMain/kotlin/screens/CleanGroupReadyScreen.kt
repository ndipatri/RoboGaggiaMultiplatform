package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CleanGroupReadyScreen(onFirstButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.clean_group_ready_primary,
        body2Resource = Res.string.clean_group_ready_secondary,
        button1Resource = Res.string.ready,
        onFirstButtonClick = onFirstButtonClick,
    )
}