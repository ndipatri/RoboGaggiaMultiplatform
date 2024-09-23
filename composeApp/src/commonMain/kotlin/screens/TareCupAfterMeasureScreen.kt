package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*
import vms.Telemetry

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TareCupAfterMeasureScreen(
    telemetry: Telemetry,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    println("*** NJD: composing TareCupAfterMeasureScreen")
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