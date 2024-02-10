package screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.myapplication.common.MR
import content.ScreenContent
import vms.UIState

@Composable
fun DoneBrewingScreen(uiState: UIState,
                      onReadyClicked: () -> Unit,
                      onExitClicked: () -> Unit) {

    BrewChart(uiState) {
        ScreenContent(
            body1Resource = MR.strings.done_brewing_primary,
            button1Resource = MR.strings.ready,
            button2Resource = MR.strings.exit,
            onFirstButtonClick = onReadyClicked,
            onSecondButtonClick = onExitClicked,
            backgroundColor = Color.Transparent
        )
    }
}

