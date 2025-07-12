package screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.core.annotation.KoinExperimentalAPI
import vms.Telemetry
import vms.TelemetryViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
fun WithLiveTelemetry(content: @Composable (Telemetry) -> Unit) {
    KoinContext {
        val viewModel = koinInject<TelemetryViewModel>()

        val telemetry by viewModel.telemetryFlow.collectAsState()

        content(telemetry)
    }
}

