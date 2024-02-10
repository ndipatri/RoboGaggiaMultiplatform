package screens

import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent
import vms.UIState

@Composable
fun TareCupAfterMeasureScreen(
    uiState: UIState,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    ScreenContent(
        body1Resource = MR.strings.tare_cup_after_measure_primary,
        body2Resource = when {
            !uiState.isCupOnlyOnScale!! ->
                MR.strings.tare_cup_after_measure_subtitle_one
            uiState.isCupOnlyOnScale ->
                MR.strings.tare_cup_after_measure_subtitle_two
            else -> null
        },
        button1Resource = if (uiState.isCupOnlyOnScale) MR.strings.done else null,
        button2Resource = MR.strings.exit,
        onFirstButtonClick = onFirstButtonClick,
        onSecondButtonClick = onSecondButtonClick
    )
}