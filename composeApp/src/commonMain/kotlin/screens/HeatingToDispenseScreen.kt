package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.exit
import robogaggiamultiplatform.composeapp.generated.resources.heating_to_dispense
import robogaggiamultiplatform.composeapp.generated.resources.please_wait
import robogaggiamultiplatform.composeapp.generated.resources.temp_is_120
import vms.Telemetry

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HeatingToDispenseScreen(telemetry: Telemetry,
                            onExitClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.heating_to_dispense,
        body2Resource = Res.string.please_wait,
        button2Resource = Res.string.exit,
        boilerIsOn = telemetry.currentBoilerIsOn ?: false,
        onSecondButtonClick = onExitClick,
    ) {
        SingleFloatContent(Res.string.temp_is_120, telemetry.currentTemperature ?: 0F)
    }}