package di

import dev.bluefalcon.ApplicationContext
import org.koin.dsl.bind
import org.koin.dsl.module
import platform.UIKit.UIView

actual val platformModule = module {
    single {
        UIView()
    }.bind<ApplicationContext>()
}