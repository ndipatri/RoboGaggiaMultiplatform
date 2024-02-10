package screens

import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent

@Composable
fun BackflushDoneScreen(onDoneClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.backflush_done_primary,
        body2Resource = MR.strings.backflush_done_subtitle,
        button1Resource = MR.strings.done,
        onFirstButtonClick = onDoneClick,
    )
}