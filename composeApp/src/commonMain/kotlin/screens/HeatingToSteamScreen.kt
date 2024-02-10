package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent
import vms.UIState

@Composable
fun HeatingToSteamScreen(uiState: UIState,
                         onSecondButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.heating_to_steam_primary,
        body2Resource = MR.strings.heating_to_steam_secondary,
        button2Resource = MR.strings.exit,
        onSecondButtonClick = onSecondButtonClick,
    ) {
        SingleFloatContent(MR.strings.heating_to_steam_content, uiState.currentTemperature ?: 0F)
    }}