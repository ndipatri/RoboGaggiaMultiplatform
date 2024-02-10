package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent
import vms.UIState

@Composable
fun DispensingHotWaterScreen(uiState: UIState,
                             onDoneClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.dispensing_hot_water_primary,
        body2Resource = MR.strings.dispensing_hot_water_subtitle,
        button1Resource = MR.strings.done,
        onFirstButtonClick = onDoneClick,
    ) {
        SingleFloatContent(MR.strings.heating_to_dispense_content, uiState.currentTemperature ?: 0F)
    }
}