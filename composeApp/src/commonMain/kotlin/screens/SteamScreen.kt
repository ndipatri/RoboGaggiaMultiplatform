package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.done
import robogaggiamultiplatform.composeapp.generated.resources.operate_steam_wand
import vms.Telemetry

@Composable
fun SteamingScreen(telemetry: Telemetry,
                   onDoneSteamingClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.operate_steam_wand,
        button1Resource = Res.string.done,
        telemetry = telemetry,
        onFirstButtonClick = onDoneSteamingClick
    )
}