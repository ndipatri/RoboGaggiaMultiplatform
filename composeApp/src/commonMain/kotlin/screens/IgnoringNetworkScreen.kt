package screens

import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent

@Composable
fun IgnoringNetworkScreen() {
    ScreenContent(
        body1Resource = MR.strings.ignoring_network_primary,
        body2Resource = MR.strings.ignoring_network_secondary
    )
}
