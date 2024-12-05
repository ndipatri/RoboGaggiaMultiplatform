package persistence

import TelemetrySerializer
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioStorage
import okio.FileSystem
import okio.Path
import robo.ndipatri.robogaggia.proto_datastore_kmm.TelemetryProtoData

internal const val TELEMETRY_DATA_STORE_FILE_NAME = "proto_datastore.telemetry_pb"

fun createDataStore(
    fileSystem: FileSystem,
    producePath: () -> Path
): DataStore<TelemetryProtoData> =
    DataStoreFactory.create(
        storage = OkioStorage(
            fileSystem = fileSystem,
            producePath = producePath,
            serializer = TelemetrySerializer,
        ),
    )