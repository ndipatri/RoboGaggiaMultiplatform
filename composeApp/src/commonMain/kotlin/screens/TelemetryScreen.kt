package screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import vms.Telemetry
import vms.TelemetryViewModel
import kotlin.reflect.KFunction

@OptIn(KoinExperimentalAPI::class)
@Composable
fun TelemetryScreen(content: @Composable (Telemetry) -> String) {
    KoinContext {
        val viewModel = koinInject<TelemetryViewModel>()

        val telemetry by viewModel.telemetryFlow.collectAsState()

        val screenName = content(telemetry)

        println("*** NJD: Composing '$screenName' with telemetry: $telemetry")
    }
}

