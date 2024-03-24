package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CleanGroupDoneScreen(onSecondButtonClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.cleaning_group,
        body2Resource = Res.string.please_wait,
        button2Resource = Res.string.exit,
        onSecondButtonClick = onSecondButtonClick
    )
}