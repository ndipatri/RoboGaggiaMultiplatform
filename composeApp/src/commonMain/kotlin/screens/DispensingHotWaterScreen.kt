package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.dispense_hot_water
import robogaggiamultiplatform.composeapp.generated.resources.done
import robogaggiamultiplatform.composeapp.generated.resources.temp_is_120
import robogaggiamultiplatform.composeapp.generated.resources.use_steam_wand_to_dispense
import vms.Telemetry

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DispensingHotWaterScreen(telemetry: Telemetry,
                             onDoneClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.dispense_hot_water,
        body2Resource = Res.string.use_steam_wand_to_dispense,
        button1Resource = Res.string.done,
        boilerIsOn = telemetry.currentBoilerIsOn ?: false,
        onFirstButtonClick = onDoneClick,
    ) {
        SingleFloatContent(Res.string.temp_is_120, telemetry.currentTemperature ?: 0F)
    }
}