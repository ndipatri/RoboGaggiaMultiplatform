package screens

import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent

@Composable
fun CleanGroupReadyScreen(onFirstButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.clean_group_ready_primary,
        body2Resource = MR.strings.clean_group_ready_secondary,
        button1Resource = MR.strings.ready,
        onFirstButtonClick = onFirstButtonClick,
    )
}