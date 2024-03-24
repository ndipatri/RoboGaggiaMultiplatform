package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TareCupAfterMeasureScreen(
    uiState: UIState,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    ScreenContent(
        body1Resource = Res.string.grind_your_beans,
        body2Resource = when {
            !uiState.isCupOnlyOnScale!! ->
                Res.string.put_empty_cup_back_on_scale
            uiState.isCupOnlyOnScale ->
                Res.string.click_done_to_begin_brewing
            else -> null
        },
        button1Resource = if (uiState.isCupOnlyOnScale) Res.string.done else null,
        button2Resource = Res.string.exit,
        onFirstButtonClick = onFirstButtonClick,
        onSecondButtonClick = onSecondButtonClick
    )
}