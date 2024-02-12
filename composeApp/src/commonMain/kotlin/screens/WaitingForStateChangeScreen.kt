package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import robogaggiamultiplatform.composeapp.generated.resources.Res

//import androidx.compose.desktop.ui.tooling.preview.Preview


@OptIn(ExperimentalResourceApi::class)
@Composable
fun WaitingForStateChangeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
//            Image(
//                modifier = Modifier.weight(.2f),
//                painter = painterResource(MR.images.coffee_face),
//                contentDescription = null
//            )
            Text(text = stringResource(Res.string.please_wait))
        }
    }
}
