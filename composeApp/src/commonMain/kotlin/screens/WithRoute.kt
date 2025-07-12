package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.core.annotation.KoinExperimentalAPI
import services.ClickSound
import services.MCPQuery
import services.SpeechToText
import vms.Telemetry
import vms.TelemetryViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
fun WithRoute(content: @Composable BoxScope.(Telemetry) -> Unit) {
    KoinContext {
        val viewModel = koinInject<TelemetryViewModel>()

        val speechToText = koinInject<SpeechToText>()
        val clickSound = koinInject<ClickSound>()
        var isListening by remember { mutableStateOf(false) }

        val telemetry by viewModel.telemetryFlow.collectAsState()

        Box(
            modifier = Modifier.fillMaxSize().background(color = Color.Transparent)
        ) {
            content(telemetry)

            FloatingActionButton(
                onClick = {
                    clickSound.play()
                    if (isListening) {
                        speechToText.stopListening()
                    } else {
                        speechToText.startListening {
                            println("*** NJD: Speech: $it")
                            viewModel.mcpQueryFlow.value = MCPQuery(it)
                        }
                    }
                    isListening = !isListening
                },
                backgroundColor = if (isListening) Color.Red else Color.Green,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(5.dp)
            ) {
                if (isListening) {
                    Icon(Icons.Default.MicOff, contentDescription = "stop recording")
                } else {
                    Icon(Icons.Default.Mic, contentDescription = "mic")
                }
            }
        }
    }
}

