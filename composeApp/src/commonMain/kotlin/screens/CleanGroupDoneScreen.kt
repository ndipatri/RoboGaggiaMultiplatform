package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CleanGroupDoneScreen(onSecondButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.clean_group_done_primary,
        body2Resource = Res.string.clean_group_done_secondary,
        button2Resource = Res.string.exit,
        onSecondButtonClick = onSecondButtonClick
    )
}