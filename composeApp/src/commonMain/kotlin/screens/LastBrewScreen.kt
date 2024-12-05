package screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import content.BrewChartContent
import content.ScreenContent
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.done
import robogaggiamultiplatform.composeapp.generated.resources.last_brew
import robogaggiamultiplatform.composeapp.generated.resources.last_brew_no_data
import vms.Telemetry

@Composable
fun LastBrewScreen(
    telemetry: Telemetry?,
    onDoneClicked: () -> Unit,
) {
    if (telemetry?.telemetry?.isNotEmpty() == true) {
        BrewChartContent(telemetry = telemetry) {
            ScreenContent(
                body1Resource = Res.string.last_brew,
                button1Resource = Res.string.done,
                button2Resource = null,
                telemetry = telemetry,
                showFireContent = false,
                onFirstButtonClick = onDoneClicked,
                backgroundImage = null,
            )
        }
    } else {
        ScreenContent(
            body1Resource = Res.string.last_brew_no_data,
            button1Resource = Res.string.done,
            button2Resource = null,
            telemetry = null,
            onFirstButtonClick = onDoneClicked,
        )
    }
}

