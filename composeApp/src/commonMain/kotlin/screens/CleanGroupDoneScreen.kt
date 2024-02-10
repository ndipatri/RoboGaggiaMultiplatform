package screens

import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent

@Composable
fun CleanGroupDoneScreen(onSecondButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.clean_group_done_primary,
        body2Resource = MR.strings.clean_group_done_secondary,
        button2Resource = MR.strings.exit,
        onSecondButtonClick = onSecondButtonClick
    )
}