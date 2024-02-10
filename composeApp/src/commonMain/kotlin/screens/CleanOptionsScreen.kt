package screens

import androidx.compose.runtime.Composable
import com.myapplication.common.MR
import content.ScreenContent

@Composable
fun CleanOptionsScreen(onDescaleClick: () -> Unit, onBackflushClick: () -> Unit) {
    ScreenContent(
        body1Resource = MR.strings.clean_options_primary,
        button1Resource = MR.strings.backflush,
        button2Resource = MR.strings.descale,
        onFirstButtonClick = onBackflushClick,
        onSecondButtonClick = onDescaleClick
    )
}