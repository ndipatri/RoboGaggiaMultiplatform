package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SteamingScreen(uiState: UIState,
                   onDoneSteamingClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.operate_steam_wand,
        button1Resource = Res.string.done,
        boilerIsOn = uiState.currentBoilerIsOn ?: false,
        onFirstButtonClick = onDoneSteamingClick
    ) {
        SingleFloatContent(Res.string.temp_is_140, uiState.currentTemperature ?: 0F)
    }
}