package di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import vms.TelemetryViewModel

expect val platformModule: Module

val sharedModule = module {
    viewModelOf(::TelemetryViewModel)
}