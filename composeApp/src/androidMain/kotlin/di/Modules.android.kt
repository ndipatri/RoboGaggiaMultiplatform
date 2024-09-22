package di

import android.app.Application
import android.content.Context
import dev.bluefalcon.ApplicationContext
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module {
    single {
        androidContext() as Application
    }.bind<ApplicationContext>()
}