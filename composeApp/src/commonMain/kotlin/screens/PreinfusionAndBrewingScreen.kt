package screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import content.BrewChartContent
import content.ScreenContent
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.done_brewing
import robogaggiamultiplatform.composeapp.generated.resources.exit
import robogaggiamultiplatform.composeapp.generated.resources.ready
import vms.GaggiaState
import vms.Telemetry

@Composable
fun PreinfusionAndBrewingScreen(
    telemetry: Telemetry,
    onReadyClicked: () -> Unit,
    onExitClicked: () -> Unit
) {
    if (telemetry.currentState in setOf(
            GaggiaState.PREINFUSION,
            GaggiaState.BREWING,
            GaggiaState.DONE_BREWING
        )
    ) {
        BrewChartContent(telemetry = telemetry) {
            val doneBrewing = telemetry.currentState == GaggiaState.DONE_BREWING
            ScreenContent(
                body1Resource = if (doneBrewing) Res.string.done_brewing else null,
                button1Resource = if (doneBrewing) Res.string.ready else null,
                button2Resource = Res.string.exit,
                telemetry = telemetry,
                onFirstButtonClick = if (doneBrewing) onReadyClicked else null,
                onSecondButtonClick = onExitClicked,
                backgroundImage = null,
                shouldUIDisappear = !doneBrewing,
                shouldAutoAdvance = doneBrewing,

                // We want the buttons from ScreenContent, but it's background needs to be
                // transparent so we can see the BrewChart behind it.
                backgroundColor = Color.Transparent
            )
        }
    }
}

