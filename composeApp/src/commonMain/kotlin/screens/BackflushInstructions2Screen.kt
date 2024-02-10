package screens

import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent

@Composable
fun BackflushInsructions2Screen(onReadyClick: () -> Unit, onExitClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.backflush_inst2_primary,
        body2Resource = MR.strings.backflush_inst2_subtitle,
        button1Resource = MR.strings.ready,
        button2Resource = MR.strings.exit,
        onFirstButtonClick = onReadyClick,
        onSecondButtonClick = onExitClick
    )
}