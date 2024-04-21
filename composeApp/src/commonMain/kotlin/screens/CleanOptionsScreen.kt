package screens

import androidx.compose.runtime.Composable
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CleanOptionsScreen(onDescaleClick: () -> Unit, onBackflushClick: () -> Unit) {
    ScreenContent(
        body1Resource = Res.string.select_cleaning_option,
        button1Resource = Res.string.descale,
        button2Resource = Res.string.backflush,
        onFirstButtonClick = onDescaleClick,
        onSecondButtonClick = onBackflushClick
    )
}