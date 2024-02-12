package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SleepScreen(onFirstButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.sleep_primary,
        body2Resource = Res.string.sleep_secondary,
    )
}
