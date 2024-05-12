package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import robogaggiamultiplatform.composeapp.generated.resources.*
import utils.toStringWithTenths
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PreheatScreen(
    uiState: UIState,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    ScreenContent(
        body1Resource = Res.string.hello_welcome_to_robogaggia,
        body2Resource = Res.string.place_your_empty_cup,
        button1Resource = Res.string.start,
        userMessage = if (uiState.currentShotsUntilBackflush == 0) {
            stringResource(Res.string.time_to_backflush)
        } else {
            uiState.currentTotalShots?.let {
                stringResource(Res.string.shots_brewed_so_far, it)
            } ?: null
        },
        boilerIsOn = uiState.currentBoilerIsOn ?: false,
        button2Resource = Res.string.clean,
        onFirstButtonClick = onFirstButtonClick,
        onSecondButtonClick = onSecondButtonClick,
        backgroundColor = androidx.compose.ui.graphics.Color.Transparent
    ) {
        SingleFloatContent(Res.string.temp_is_120, uiState.currentTemperature ?: 0F)
    }
}
