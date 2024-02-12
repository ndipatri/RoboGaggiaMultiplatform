package screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DoneBrewingScreen(uiState: UIState,
                      onReadyClicked: () -> Unit,
                      onExitClicked: () -> Unit) {

    BrewChart(uiState) {
        ScreenContent(
            body1Resource = Res.string.done_brewing_primary,
            button1Resource = Res.string.ready,
            button2Resource = Res.string.exit,
            onFirstButtonClick = onReadyClicked,
            onSecondButtonClick = onExitClicked,
            backgroundColor = Color.Transparent
        )
    }
}

