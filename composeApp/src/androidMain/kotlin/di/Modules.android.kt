package di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
    single {
        ApplicationContextWrapper(androidContext() as Application)
    }.bind<ApplicationContextWrapper>()
}