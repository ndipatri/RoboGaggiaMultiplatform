package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*
import vms.Telemetry


@OptIn(ExperimentalResourceApi::class)
@Composable
fun MeasureBeansScreen(
    telemetry: Telemetry,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    println("*** NJD: composing MeasureBeansScreen")
    ScreenContent(
        body1Resource = Res.string.weigh_your_beans,
        body2Resource = if (!telemetry.isScaleWeighted)
            Res.string.add_whole_beans
        else if (!telemetry.isScaleSettled)
            Res.string.weighing
        else
            Res.string.click_done_to_grind_beans,
        button1Resource = if (telemetry.isScaleWeighted) Res.string.done else null,
        button2Resource = Res.string.exit,
        boilerIsOn = telemetry.currentBoilerIsOn ?: false,
        onFirstButtonClick = onFirstButtonClick,
        onSecondButtonClick = onSecondButtonClick
    ) {
        if (telemetry.isScaleWeightedRaw) {
            SingleFloatContent(Res.string.beans_weigh, telemetry.currentTaredWeight ?: 0F)
        }
    }
}