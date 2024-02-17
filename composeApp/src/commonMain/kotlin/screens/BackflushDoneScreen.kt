package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BackflushDoneScreen(onDoneClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.backflush_done_primary,
        body2Resource = Res.string.backflush_done_subtitle,
        button1Resource = Res.string.done,
        onFirstButtonClick = onDoneClick,
    )
}