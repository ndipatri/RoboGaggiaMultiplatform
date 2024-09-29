package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.add_whole_beans
import robogaggiamultiplatform.composeapp.generated.resources.beans_weigh
import robogaggiamultiplatform.composeapp.generated.resources.click_done_to_grind_beans
import robogaggiamultiplatform.composeapp.generated.resources.done
import robogaggiamultiplatform.composeapp.generated.resources.exit
import robogaggiamultiplatform.composeapp.generated.resources.weigh_your_beans
import robogaggiamultiplatform.composeapp.generated.resources.weighing
import vms.Telemetry

@Composable
fun MeasureBeansScreen(
    telemetry: Telemetry,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
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