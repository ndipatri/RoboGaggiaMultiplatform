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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.core.annotation.KoinExperimentalAPI
import services.ClickSound
import services.MCPQuery
import services.SpeechToText
import vms.Telemetry
import vms.TelemetryViewModel

@OptIn(KoinExperimentalAPI::class, FlowPreview::class)
@Composable
fun WithRoute(content: @Composable BoxScope.(Telemetry) -> Unit) {
    KoinContext {
        val viewModel = koinInject<TelemetryViewModel>()

        val speechToText = koinInject<SpeechToText>()
        val clickSound = koinInject<ClickSound>()
        var isListening by remember { mutableStateOf(false) }

        val telemetry by viewModel.telemetryFlow.collectAsState()

        val speechToTextResults = remember { MutableStateFlow<String?>(null) }

        // Continuously monitor incoming speech
        LaunchedEffect(true) {
            while (isActive) {
                println("*** NJD: Listening to audio ...")

                // NJD TODO - remove this.. wrong place
                viewModel.mcpQueryFlow.value = MCPQuery("")

                coroutineScope {
                    launch {
//                        speechToText.startListening {
//                            println("*** NJD: Speech: $it")
//                            speechToTextResults.value = it
//                        }

                        println("*** NJD: Waiting for wake word ...")
                        speechToTextResults.filter { it?.contains("Gaggia") ?: false }
                            .debounce(1000)
                            .collect {
                                println("*** NJD: Wake word detected.")
                                speechToText.stopListening()
                                clickSound.play()
                                isListening = true
                                print("*** NJD: Gaggia command: $it")

                                // handle command...
                                //viewModel.mcpQueryFlow.value = MCPQuery(it)

                                delay(5000)
                                print("*** NJD: Done processing request.")
                                isListening = false
                                clickSound.play()
                                speechToTextResults.value = null
                                cancel()
                            }
                    }
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize().background(color = Color.Transparent)
        ) {
            print("*** NJD: recompose1")
            content(telemetry)

            FloatingActionButton(
                backgroundColor = if (isListening) Color.Red else Color.Green,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(5.dp),
                onClick = {},
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

