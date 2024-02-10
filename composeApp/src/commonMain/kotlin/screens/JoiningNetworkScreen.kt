package screens

import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent

@Composable
fun JoiningNetworkScreen(onIgnoreNetworkClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.joining_network_primary,
        body2Resource = MR.strings.joining_network_secondary,
        button2Resource = MR.strings.ignore_network,
        onSecondButtonClick = onIgnoreNetworkClick
    )
}