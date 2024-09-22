package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*
import vms.Telemetry

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HeatingToBrewScreen(telemetry: Telemetry,
                        onSecondButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.heating_to_brew,
        body2Resource = Res.string.please_wait,
        button2Resource = Res.string.exit,
        boilerIsOn = telemetry.currentBoilerIsOn ?: false,
        onSecondButtonClick = onSecondButtonClick,
    ) {
        SingleFloatContent(Res.string.temp_is_120, telemetry.currentTemperature ?: 0F)
    }
}
