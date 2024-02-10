package screens

import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent

@Composable
fun DescaleScreen(onReadyClick: () -> Unit, onExitClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.descale_primary,
        body2Resource = MR.strings.descale_subtitle,
        button1Resource = MR.strings.ready,
        button2Resource = MR.strings.exit,
        onFirstButtonClick = onReadyClick,
        onSecondButtonClick = onExitClick
    )
}