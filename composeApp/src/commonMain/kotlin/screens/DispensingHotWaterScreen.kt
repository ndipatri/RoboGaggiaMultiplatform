package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DispensingHotWaterScreen(uiState: UIState,
                             onDoneClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.dispensing_hot_water_primary,
        body2Resource = Res.string.dispensing_hot_water_subtitle,
        button1Resource = Res.string.done,
        onFirstButtonClick = onDoneClick,
    ) {
        SingleFloatContent(Res.string.heating_to_dispense_content, uiState.currentTemperature ?: 0F)
    }
}