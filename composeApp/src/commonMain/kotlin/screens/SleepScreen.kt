package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import robogaggiamultiplatform.composeapp.generated.resources.*

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SleepScreen(onWakeClicked: () -> Unit) {
    ScreenContent(
        button1Resource = Res.string.wake,
        onFirstButtonClick = onWakeClicked,
        backgroundImage = Res.drawable.night
    ) {
        Column {
            Row {
                Text(
                    modifier = Modifier.padding(top = 50.dp),
                    textAlign = TextAlign.Left,
                    text = stringResource(Res.string.clear_scale)
                )
            }
        }
    }
}
