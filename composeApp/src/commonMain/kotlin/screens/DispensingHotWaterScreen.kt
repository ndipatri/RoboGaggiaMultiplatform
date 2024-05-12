package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DispensingHotWaterScreen(uiState: UIState,
                             onDoneClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.dispense_hot_water,
        body2Resource = Res.string.use_steam_wand_to_dispense,
        button1Resource = Res.string.done,
        boilerIsOn = uiState.currentBoilerIsOn ?: false,
        onFirstButtonClick = onDoneClick,
    ) {
        SingleFloatContent(Res.string.temp_is_120, uiState.currentTemperature ?: 0F)
    }
}