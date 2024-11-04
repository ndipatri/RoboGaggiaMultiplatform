package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.exit
import robogaggiamultiplatform.composeapp.generated.resources.heating_to_brew
import robogaggiamultiplatform.composeapp.generated.resources.please_wait
import vms.Telemetry

@Composable
fun HeatingToBrewScreen(telemetry: Telemetry,
                        onSecondButtonClick: () -> Unit) {

    ScreenContent(
        body1Resource = Res.string.heating_to_brew,
        body2Resource = Res.string.please_wait,
        button2Resource = Res.string.exit,
        telemetry = telemetry,
        onSecondButtonClick = onSecondButtonClick,
    )
}
