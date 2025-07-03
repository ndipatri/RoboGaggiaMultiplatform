package di

import kotlinx.cinterop.ExperimentalForeignApi
import okio.FileSystem
import okio.Path.Companion.toPath
import org.koin.dsl.bind
import org.koin.dsl.module
import persistence.createDataStore
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIView
import robo.ndipatri.robogaggia.proto_datastore_kmm.TelemetryProtoData
import services.SpeechToText

actual val platformModule = module {
    single {
        ApplicationContextWrapper(UIView())
    }.bind<ApplicationContextWrapper>()
}

@OptIn(ExperimentalForeignApi::class)
actual val dataStoreModule = module {
    single {
        val producePath = {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/$DATA_STORE_FILE_NAME"
        }

        createDataStore(fileSystem = FileSystem.SYSTEM, producePath = { producePath().toPath() })
    }
}

actual val speechToTextModule = module {
    single { SpeechToText(get<ApplicationContextWrapper>().applicationContext) }
}
