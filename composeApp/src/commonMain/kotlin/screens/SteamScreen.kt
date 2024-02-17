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
        body1Resource = Res.string.steaming_primary,
        button1Resource = Res.string.done,
        onFirstButtonClick = onDoneSteamingClick
    ) {
        SingleFloatContent(Res.string.heating_to_steam_content, uiState.currentTemperature ?: 0F)
    }
}