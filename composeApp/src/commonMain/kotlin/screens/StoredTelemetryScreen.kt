package screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.core.annotation.KoinExperimentalAPI
import robo.ndipatri.robogaggia.proto_datastore_kmm.TelemetryMessage
import robo.ndipatri.robogaggia.proto_datastore_kmm.TelemetryProtoData
import vms.Telemetry
import vms.TelemetryViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
fun StoredTelemetryScreen(content: @Composable (Telemetry?) -> Unit) {
    KoinContext {
        val telemetryDataStore = koinInject<DataStore<TelemetryProtoData>>()
        val changedTelemetryFlow = telemetryDataStore.data as Flow<TelemetryProtoData>
        val changedTelemetry by changedTelemetryFlow.map { Telemetry(it) }.collectAsState(initial = null)

        content(changedTelemetry)
    }
}

