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
        body1Resource = Res.string.heating_to_steam_primary,
        body2Resource = Res.string.heating_to_steam_secondary,
        button2Resource = Res.string.exit,
        onSecondButtonClick = onSecondButtonClick,
    ) {
        SingleFloatContent(Res.string.heating_to_steam_content, uiState.currentTemperature ?: 0F)
    }}