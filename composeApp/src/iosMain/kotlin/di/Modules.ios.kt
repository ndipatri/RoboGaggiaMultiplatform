package di

import org.koin.dsl.bind
import org.koin.dsl.module
import platform.UIKit.UIView

actual val platformModule = module {
    single {
        ApplicationContextWrapper(UIView())
    }.bind<ApplicationContextWrapper>()
}