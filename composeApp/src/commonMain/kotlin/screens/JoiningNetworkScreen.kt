package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun JoiningNetworkScreen(onIgnoreNetworkClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.joining_network_primary,
        body2Resource = Res.string.joining_network_secondary,
        button2Resource = Res.string.ignore_network,
        onSecondButtonClick = onIgnoreNetworkClick
    )
}