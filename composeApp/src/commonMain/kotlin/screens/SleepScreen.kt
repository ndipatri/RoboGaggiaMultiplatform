package screens

import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent

@Composable
fun SleepScreen(onFirstButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.sleep_primary,
        body2Resource = MR.strings.sleep_secondary,
    )
}
