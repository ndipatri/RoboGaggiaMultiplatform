package screens

import DoubleIntContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.backflushing_with_water
import robogaggiamultiplatform.composeapp.generated.resources.exit
import robogaggiamultiplatform.composeapp.generated.resources.pass_number
import robogaggiamultiplatform.composeapp.generated.resources.please_wait
import vms.Telemetry

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BackflushCycle2Screen(telemetry: Telemetry,
                          onExitClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.backflushing_with_water,
        body2Resource = Res.string.please_wait,
        button2Resource = Res.string.exit,
        onSecondButtonClick = onExitClick
    ) {
        // When in this state, the telemetry weight is the currentPass and the
        // telemetry pressure is the target number of passes.
        // We have to round up given the nature of this 'averaged' telemetry
        DoubleIntContent(Res.string.pass_number, telemetry.currentTaredWeight?.plus(0.5)?.toInt() ?: 0, telemetry.currentPressure?.toInt() ?: 0)
    }
}