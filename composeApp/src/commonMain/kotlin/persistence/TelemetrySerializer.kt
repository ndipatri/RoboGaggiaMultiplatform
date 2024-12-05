import androidx.datastore.core.okio.OkioSerializer
import okio.BufferedSink
import okio.BufferedSource
import okio.IOException
import robo.ndipatri.robogaggia.proto_datastore_kmm.TelemetryProtoData

object TelemetrySerializer : OkioSerializer<TelemetryProtoData> {
    override val defaultValue: TelemetryProtoData
        get() = TelemetryProtoData()

    override suspend fun readFrom(source: BufferedSource): TelemetryProtoData {
        try {
            return TelemetryProtoData.ADAPTER.decode(source)
        } catch (exception: IOException) {
            throw Exception(exception.message ?: "Serialization Exception")
        }
    }

    override suspend fun writeTo(t: TelemetryProtoData, sink: BufferedSink) {
        sink.write(t.encode())
    }
}