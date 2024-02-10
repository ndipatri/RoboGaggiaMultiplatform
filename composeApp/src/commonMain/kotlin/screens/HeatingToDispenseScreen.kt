package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent
import vms.UIState

@Composable
fun HeatingToDispenseScreen(uiState: UIState,
                            onExitClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.heating_to_dispense_primary,
        body2Resource = MR.strings.please_wait,
        button2Resource = MR.strings.exit,
        onSecondButtonClick = onExitClick,
    ) {
        SingleFloatContent(MR.strings.heating_to_dispense_content, uiState.currentTemperature ?: 0F)
    }}