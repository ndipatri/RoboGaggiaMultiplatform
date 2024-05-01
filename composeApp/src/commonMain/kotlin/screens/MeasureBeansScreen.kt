package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*
import vms.UIState


@OptIn(ExperimentalResourceApi::class)
@Composable
fun MeasureBeansScreen(
    uiState: UIState,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    ScreenContent(
        body1Resource = Res.string.weigh_your_beans,
        body2Resource = if (!uiState.isScaleWeighted)
            Res.string.add_whole_beans
        else if (!uiState.isScaleSettled)
            Res.string.weighing
        else
            Res.string.click_done_to_grind_beans,
        button1Resource = if (uiState.isScaleWeighted) Res.string.done else null,
        button2Resource = Res.string.exit,
        boilerIsOn = uiState.currentBoilerIsOn ?: false,
        onFirstButtonClick = onFirstButtonClick,
        onSecondButtonClick = onSecondButtonClick
    ) {
        if (uiState.isScaleWeightedRaw) {
            SingleFloatContent(Res.string.beans_weigh, uiState.currentTaredWeight ?: 0F)
        }
    }
}