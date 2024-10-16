package di

import dev.bluefalcon.ApplicationContext
import org.koin.core.module.Module
import org.koin.dsl.module
import vms.TelemetryViewModel

expect val platformModule: Module

// Currently, you cannot inject an Object-C object using KOIN,
// so you have to wrap it in a Kotlin class...
// see: https://github.com/InsertKoinIO/koin/issues/1492
data class ApplicationContextWrapper(val applicationContext: ApplicationContext)

val sharedModule = module {
    // It's tempting to use viewModelOf() here, but this
    // view model should not be tied to the lifecycle of
    // any UI element.. rather, it's an application wide
    // singleton.. It really should be a middleware component,
    // but we're calling it a viewModel...
    single { TelemetryViewModel(get<ApplicationContextWrapper>().applicationContext) }
}