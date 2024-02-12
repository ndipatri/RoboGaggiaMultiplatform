package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HeatingToBrewScreen(uiState: UIState,
                        onSecondButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.heating_to_brew_primary,
        body2Resource = Res.string.heating_to_brew_secondary,
        button2Resource = Res.string.exit,
        onSecondButtonClick = onSecondButtonClick,
    ) {
        SingleFloatContent(Res.string.heating_to_brew_content, uiState.currentTemperature ?: 0F)
    }
}
