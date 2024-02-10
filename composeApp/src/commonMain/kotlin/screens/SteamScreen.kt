package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent
import vms.UIState

@Composable
fun SteamingScreen(uiState: UIState,
                   onDoneSteamingClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.steaming_primary,
        button1Resource = MR.strings.done,
        onFirstButtonClick = onDoneSteamingClick
    ) {
        SingleFloatContent(MR.strings.heating_to_steam_content, uiState.currentTemperature ?: 0F)
    }
}