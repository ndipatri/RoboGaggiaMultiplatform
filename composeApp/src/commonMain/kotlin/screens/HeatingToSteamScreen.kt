package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HeatingToSteamScreen(uiState: UIState,
                         onSecondButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.heating_to_steam,
        body2Resource = Res.string.remove_and_clean_portafilter,
        button2Resource = Res.string.exit,
        boilerIsOn = uiState.currentBoilerIsOn ?: false,
        onSecondButtonClick = onSecondButtonClick,
    ) {
        SingleFloatContent(Res.string.temp_is_140, uiState.currentTemperature ?: 0F)
    }}