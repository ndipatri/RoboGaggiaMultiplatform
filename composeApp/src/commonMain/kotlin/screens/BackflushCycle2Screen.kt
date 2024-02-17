package screens

import DoubleIntContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BackflushCycle2Screen(uiState: UIState,
                          onExitClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.backflush_cycle2_primary,
        body2Resource = Res.string.backflush_cycle2_subtitle,
        button2Resource = Res.string.exit,
        onSecondButtonClick = onExitClick
    ) {
        // When in this state, the telemetry weight is the currentPass and the
        // telemetry pressure is the target number of passes.
        // We have to round up given the nature of this 'averaged' telemetry
        DoubleIntContent(Res.string.backflush_cycle1_content, uiState.currentTaredWeight?.plus(0.5)?.toInt() ?: 0, uiState.currentPressure?.toInt() ?: 0)
    }
}