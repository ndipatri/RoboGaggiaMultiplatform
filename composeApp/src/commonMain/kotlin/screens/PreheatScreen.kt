package screens

import SingleFloatContent
import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent
import vms.UIState

@Composable
fun PreheatScreen(
    uiState: UIState,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    ScreenContent(
        body1Resource = MR.strings.preheat_primary,
        body2Resource = if (!uiState.isScaleWeighted) MR.strings.preheat_subtitle_one else null,
        button1Resource = if (uiState.isScaleWeighted) MR.strings.preheat_cta else null,
        button2Resource = MR.strings.preheat_cta2,
        onFirstButtonClick = onFirstButtonClick,
        onSecondButtonClick = onSecondButtonClick
    ) {
        SingleFloatContent(MR.strings.preheat_content, uiState.currentTemperature ?: 0F)
    }
}
