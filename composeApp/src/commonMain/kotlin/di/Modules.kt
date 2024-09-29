package di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import vms.TelemetryViewModel

expect val platformModule: Module

val sharedModule = module {
    // It's tempting to use viewModelOf() here, but this
    // view model should not be tied to the lifecycle of
    // any UI element.. rather, it's an application wide
    // singleton.. It really should be a middleware component,
    // but we're calling it a viewModel...
    single { TelemetryViewModel(get()) }
}