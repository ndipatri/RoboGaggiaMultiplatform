package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SleepScreen(onWakeClicked: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.i_am_sleeping,
        body2Resource = Res.string.clear_scale,
        button1Resource = Res.string.wake,
        onFirstButtonClick = onWakeClicked,
        backgroundImage = Res.drawable.night
    )
}
