package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import vms.UIState


@OptIn(ExperimentalResourceApi::class)
@Composable
fun MeasureBeansScreen(
    uiState: UIState,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    ScreenContent(
        body1Resource = Res.string.measure_beans_primary,
        body2Resource = if (!uiState.isScaleWeighted)
            Res.string.measure_beans_subtitle_one
        else if (!uiState.isScaleSettled)
            Res.string.measure_beans_subtitle_two
        else
            Res.string.measure_beans_subtitle_three,
        button1Resource = if (uiState.isScaleWeighted) Res.string.done else null,
        button2Resource = Res.string.exit,
        onFirstButtonClick = onFirstButtonClick,
        onSecondButtonClick = onSecondButtonClick
    ) {
        if (uiState.isScaleWeightedRaw) {
            SingleFloatContent(Res.string.measure_beans_content, uiState.currentTaredWeight ?: 0F)
        }
    }
}