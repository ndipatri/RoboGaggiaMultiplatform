package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun IgnoringNetworkScreen() {
    ScreenContent(
        body1Resource = Res.string.ignoring_network,
        body2Resource = Res.string.clear_scale
    )
}
