package di

import android.app.Application
import androidx.datastore.core.DataStore
import okio.FileSystem
import okio.Path.Companion.toPath
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import persistence.createDataStore
import robo.ndipatri.robogaggia.proto_datastore_kmm.TelemetryProtoData

actual val platformModule = module {
    single {
        ApplicationContextWrapper(androidContext() as Application)
    }.bind<ApplicationContextWrapper>()
}

actual val dataStoreModule = module {
    single {
        val producePath = { androidContext().filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath.toPath() }
        createDataStore(fileSystem = FileSystem.SYSTEM, producePath = producePath)
    }
}
