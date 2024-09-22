package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.done
import robogaggiamultiplatform.composeapp.generated.resources.operate_steam_wand
import robogaggiamultiplatform.composeapp.generated.resources.temp_is_140
import vms.Telemetry

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SteamingScreen(telemetry: Telemetry,
                   onDoneSteamingClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.operate_steam_wand,
        button1Resource = Res.string.done,
        boilerIsOn = telemetry.currentBoilerIsOn ?: false,
        onFirstButtonClick = onDoneSteamingClick
    ) {
        SingleFloatContent(Res.string.temp_is_140, telemetry.currentTemperature ?: 0F)
    }
}