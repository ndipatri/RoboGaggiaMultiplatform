package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.core.annotation.KoinExperimentalAPI
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.clean
import robogaggiamultiplatform.composeapp.generated.resources.hello_welcome_to_robogaggia
import robogaggiamultiplatform.composeapp.generated.resources.last_brew
import robogaggiamultiplatform.composeapp.generated.resources.place_your_empty_cup
import robogaggiamultiplatform.composeapp.generated.resources.settings
import robogaggiamultiplatform.composeapp.generated.resources.shots_brewed_so_far
import robogaggiamultiplatform.composeapp.generated.resources.start
import robogaggiamultiplatform.composeapp.generated.resources.time_to_backflush
import vms.Telemetry

@Composable
fun PreheatScreen(
    telemetry: Telemetry,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
    onSettingsSelected: () -> Unit,
    onLastBrewSelected: () -> Unit
) {
    ScreenContent(
        body1Resource = Res.string.hello_welcome_to_robogaggia,
        body2Resource = Res.string.place_your_empty_cup,
        button1Resource = Res.string.start,
        userMessage = if (telemetry.currentShotsUntilBackflush == 0) {
            stringResource(Res.string.time_to_backflush)
        } else {
            telemetry.currentTotalShots?.let {
                stringResource(Res.string.shots_brewed_so_far, it)
            } ?: null
        },
        telemetry = telemetry,
        button2Resource = Res.string.clean,
        button3Resource = Res.string.settings,
        button4Resource = Res.string.last_brew,
        onFirstButtonClick = onFirstButtonClick,
        onSecondButtonClick = onSecondButtonClick,
        onThirdButtonClick = onSettingsSelected,
        onFourthButtonClick = onLastBrewSelected,
        backgroundColor = androidx.compose.ui.graphics.Color.Transparent
    )
}
