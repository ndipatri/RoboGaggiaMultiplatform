package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent
import vms.UIState


@Composable
fun MeasureBeansScreen(
    uiState: UIState,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    ScreenContent(
        body1Resource = MR.strings.measure_beans_primary,
        body2Resource = if (!uiState.isScaleWeighted)
            MR.strings.measure_beans_subtitle_one
        else if (!uiState.isScaleSettled)
            MR.strings.measure_beans_subtitle_two
        else
            MR.strings.measure_beans_subtitle_three,
        button1Resource = if (uiState.isScaleWeighted) MR.strings.done else null,
        button2Resource = MR.strings.exit,
        onFirstButtonClick = onFirstButtonClick,
        onSecondButtonClick = onSecondButtonClick
    ) {
        if (uiState.isScaleWeightedRaw) {
            SingleFloatContent(MR.strings.measure_beans_content, uiState.currentTaredWeight ?: 0F)
        }
    }
}