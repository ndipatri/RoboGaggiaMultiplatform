package screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import content.BrewChartContent
import content.ScreenContent
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.exit
import robogaggiamultiplatform.composeapp.generated.resources.heating_to_steam
import robogaggiamultiplatform.composeapp.generated.resources.remove_and_clean_portafilter
import vms.Telemetry

@Composable
fun HeatingToSteamScreen(
    telemetry: Telemetry,
    onSecondButtonClick: () -> Unit
) {
    BrewChartContent(telemetry = telemetry) {
        ScreenContent(
            body1Resource = Res.string.heating_to_steam,
            body2Resource = Res.string.remove_and_clean_portafilter,
            button2Resource = Res.string.exit,
            telemetry = telemetry,
            onSecondButtonClick = onSecondButtonClick,
            backgroundImage = null,

            // We want the buttons from ScreenContent, but it's background needs to be
            // transparent so we can see the BrewChart behind it.
            backgroundColor = Color.Transparent
        )
    }
}
