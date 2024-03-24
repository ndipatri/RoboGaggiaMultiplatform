package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DescaleScreen(onReadyClick: () -> Unit, onExitClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.descale_boiler,
        body2Resource = Res.string.fill_water_reservouir_with_descale,
        button1Resource = Res.string.ready,
        button2Resource = Res.string.exit,
        onFirstButtonClick = onReadyClick,
        onSecondButtonClick = onExitClick
    )
}