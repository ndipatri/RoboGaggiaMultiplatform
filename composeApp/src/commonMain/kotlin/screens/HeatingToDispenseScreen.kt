package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HeatingToDispenseScreen(uiState: UIState,
                            onExitClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.heating_to_dispense_primary,
        body2Resource = Res.string.please_wait,
        button2Resource = Res.string.exit,
        onSecondButtonClick = onExitClick,
    ) {
        SingleFloatContent(Res.string.heating_to_dispense_content, uiState.currentTemperature ?: 0F)
    }}