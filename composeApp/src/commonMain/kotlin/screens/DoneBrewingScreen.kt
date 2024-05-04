package screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.done_brewing
import robogaggiamultiplatform.composeapp.generated.resources.exit
import robogaggiamultiplatform.composeapp.generated.resources.ready
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DoneBrewingScreen(uiState: UIState,
                      onReadyClicked: () -> Unit,
                      onExitClicked: () -> Unit) {

    BrewChart(uiState = uiState, showCounter = false) {
        ScreenContent(
            body1Resource = Res.string.done_brewing,
            button1Resource = Res.string.ready,
            button2Resource = Res.string.exit,
            onFirstButtonClick = onReadyClicked,
            onSecondButtonClick = onExitClicked,
            backgroundImage = null,
            shouldUIDisappear = true,

            // We want the buttons from ScreenContent, but it's background needs to be
            // transparent so we can see the BrewChart behind it.
            backgroundColor = Color.Transparent
        )
    }
}

