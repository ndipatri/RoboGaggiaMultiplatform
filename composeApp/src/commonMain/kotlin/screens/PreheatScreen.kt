package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Color
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
        onSecondButtonClick = onSecondButtonClick,
        backgroundColor = androidx.compose.ui.graphics.Color.Transparent
    ) {
        SingleFloatContent(Res.string.preheat_content, uiState.currentTemperature ?: 0F)
    }
}
