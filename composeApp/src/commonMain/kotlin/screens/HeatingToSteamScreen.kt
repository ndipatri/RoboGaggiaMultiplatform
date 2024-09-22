package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.exit
import robogaggiamultiplatform.composeapp.generated.resources.heating_to_steam
import robogaggiamultiplatform.composeapp.generated.resources.remove_and_clean_portafilter
import robogaggiamultiplatform.composeapp.generated.resources.temp_is_140
import vms.Telemetry

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HeatingToSteamScreen(telemetry: Telemetry,
                         onSecondButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.heating_to_steam,
        body2Resource = Res.string.remove_and_clean_portafilter,
        button2Resource = Res.string.exit,
        boilerIsOn = telemetry.currentBoilerIsOn ?: false,
        onSecondButtonClick = onSecondButtonClick,
    ) {
        SingleFloatContent(Res.string.temp_is_140, telemetry.currentTemperature ?: 0F)
    }}