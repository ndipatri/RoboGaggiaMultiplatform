package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*
import utils.toStringWithTenths
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PreheatScreen(
    uiState: UIState,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    ScreenContent(
        body1Resource = Res.string.preheat_primary,
        body2Resource = if (!uiState.isScaleWeighted) Res.string.preheat_subtitle_one else null,
        button1Resource = if (uiState.isScaleWeighted) Res.string.preheat_cta else null,
        button2Resource = Res.string.preheat_cta2,
        onFirstButtonClick = onFirstButtonClick,
        onSecondButtonClick = onSecondButtonClick
    ) {
        println("*** VM: updating content in preheat screen with temp: ${uiState.currentTemperature?.toStringWithTenths()}")
        SingleFloatContent(Res.string.preheat_content, uiState.currentTemperature ?: 0F)
    }
}
