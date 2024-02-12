package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TareCupAfterMeasureScreen(
    uiState: UIState,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    ScreenContent(
        body1Resource = Res.string.tare_cup_after_measure_primary,
        body2Resource = when {
            !uiState.isCupOnlyOnScale!! ->
                Res.string.tare_cup_after_measure_subtitle_one
            uiState.isCupOnlyOnScale ->
                Res.string.tare_cup_after_measure_subtitle_two
            else -> null
        },
        button1Resource = if (uiState.isCupOnlyOnScale) Res.string.done else null,
        button2Resource = Res.string.exit,
        onFirstButtonClick = onFirstButtonClick,
        onSecondButtonClick = onSecondButtonClick
    )
}