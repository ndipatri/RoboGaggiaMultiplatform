package screens

import DoubleIntContent
import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent
import vms.UIState

@Composable
fun BackflushCycle2Screen(uiState: UIState,
                          onExitClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.backflush_cycle2_primary,
        body2Resource = MR.strings.backflush_cycle2_subtitle,
        button2Resource = MR.strings.exit,
        onSecondButtonClick = onExitClick
    ) {
        // When in this state, the telemetry weight is the currentPass and the
        // telemetry pressure is the target number of passes.
        // We have to round up given the nature of this 'averaged' telemetry
        DoubleIntContent(MR.strings.backflush_cycle1_content, uiState.currentTaredWeight?.plus(0.5)?.toInt() ?: 0, uiState.currentPressure?.toInt() ?: 0)
    }
}