package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SleepScreen(onWakeClicked: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.sleep_primary,
        button1Resource = Res.string.wake,
        onFirstButtonClick = onWakeClicked,
        backgroundImage = Res.drawable.night
    )
}
