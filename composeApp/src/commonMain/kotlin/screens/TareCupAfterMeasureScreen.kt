package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.click_done_to_begin_brewing
import robogaggiamultiplatform.composeapp.generated.resources.done
import robogaggiamultiplatform.composeapp.generated.resources.exit
import robogaggiamultiplatform.composeapp.generated.resources.grind_your_beans
import robogaggiamultiplatform.composeapp.generated.resources.put_empty_cup_back_on_scale
import vms.Telemetry

@Composable
fun TareCupAfterMeasureScreen(
    telemetry: Telemetry,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    ScreenContent(
        body1Resource = Res.string.grind_your_beans,
        body2Resource = when {
            !telemetry.isCupOnlyOnScale ->
                Res.string.put_empty_cup_back_on_scale

            telemetry.isCupOnlyOnScale ->
                Res.string.click_done_to_begin_brewing

            else -> null
        },
        button1Resource = if (telemetry.isCupOnlyOnScale) Res.string.done else null,
        button2Resource = Res.string.exit,
        boilerIsOn = telemetry.currentBoilerIsOn ?: false,
        onFirstButtonClick = onFirstButtonClick,
        onSecondButtonClick = onSecondButtonClick
    )
}