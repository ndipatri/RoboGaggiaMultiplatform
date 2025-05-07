package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import content.WelcomeAnimation
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.welcome_primary

@OptIn(ExperimentalResourceApi::class)
@Composable
fun WelcomeScreen() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        WelcomeAnimation(Modifier.weight(.5f).fillMaxHeight().background(Color.Black))
        Text(
            modifier = Modifier.weight(.5f).padding(20.dp),
            textAlign = TextAlign.Center,
            text = stringResource(Res.string.welcome_primary)
        )
    }
}
