package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun JoiningNetworkScreen(onIgnoreNetworkClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.joining_network,
        body2Resource = Res.string.clear_scale,
        button2Resource = Res.string.ignore_network,
        onSecondButtonClick = onIgnoreNetworkClick
    )
}