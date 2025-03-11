package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import content.SleepAnimation
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.coffee_robot
import robogaggiamultiplatform.composeapp.generated.resources.welcome_primary

@OptIn(ExperimentalResourceApi::class)
@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (verticalAlignment = Alignment.CenterVertically) {
//            Image(
//                modifier = Modifier.weight(.2f),
//                painter = painterResource(Res.drawable.coffee_robot),
//                contentDescription = null
//            )
            SleepAnimation(Modifier.weight(.2f))
            Text(
                modifier = Modifier.width(460.dp).padding(horizontal = 30.dp),
                textAlign = TextAlign.Center,
                text = stringResource(Res.string.welcome_primary)
            )
        }
    }
}
