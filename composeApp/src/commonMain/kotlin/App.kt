import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.core.annotation.KoinExperimentalAPI
import screens.BackflushCycle1Screen
import screens.BackflushCycle2Screen
import screens.BackflushDoneScreen
import screens.BackflushInsructions1Screen
import screens.BackflushInsructions2Screen
import screens.BackflushInsructions3Screen
import screens.CleanGroupDoneScreen
import screens.CleanGroupReadyScreen
import screens.CleanOptionsScreen
import screens.DescaleScreen
import screens.DispensingHotWaterScreen
import screens.HeatingToBrewScreen
import screens.HeatingToDispenseScreen
import screens.HeatingToSteamScreen
import screens.IgnoringNetworkScreen
import screens.JoiningNetworkScreen
import screens.LastBrewScreen
import screens.MeasureBeansScreen
import screens.PreheatScreen
import screens.PreinfusionAndBrewingScreen
import screens.SettingsScreen
import screens.SleepScreen
import screens.SteamingScreen
import screens.TareCupAfterMeasureScreen
import screens.LiveTelemetryScreen
import screens.StoredTelemetryScreen
import screens.WaitingForStateChangeScreen
import screens.WelcomeScreen
import theme.RoboGaggiaTheme
import vms.GaggiaState
import vms.TelemetryViewModel

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(bluetoothPermissionAcquired: Boolean) {
    AppContent(bluetoothPermissionAcquired)
}

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AppContent(bluetoothPermissionAcquired: Boolean) {
    val navController: NavHostController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        RoboGaggiaTheme(darkTheme = true) {
            KoinContext {
                val viewModel = koinInject<TelemetryViewModel>()

                val telemetry by viewModel.telemetryFlow.collectAsState()

                var waitingToChangeFromState: GaggiaState by remember { mutableStateOf(GaggiaState.NA) }

                val onFirstButtonClick = { viewModel.firstButtonClick() }
                val onSecondButtonClick = { viewModel.secondButtonClick() }

                val _onFirstButtonClick = {
                    waitingToChangeFromState = telemetry.currentState ?: GaggiaState.NA
                    onFirstButtonClick()
                }

                val _onSecondButtonClick = {
                    waitingToChangeFromState = telemetry.currentState ?: GaggiaState.NA
                    onSecondButtonClick()
                }

                // This is a route driven by user (e.g. Settings), not by telemetry from Gaggia
                val localRouteSelected = remember { mutableStateOf<String?>(null) }

                // If this is set to true and BLE is enabled via build config,
                // then bluetooth scanning will begin
                if (!viewModel.bluetoothPermissionAcquired && bluetoothPermissionAcquired) {
                    viewModel.bluetoothPermissionAcquired = bluetoothPermissionAcquired
                }

                LaunchedEffect(telemetry.currentState, waitingToChangeFromState, localRouteSelected.value) {
                    var route: String? = null

                    localRouteSelected.value?.let {
                        route = it
                        localRouteSelected.value = null
                    } ?: let {
                        // no request to navigate to a new local route...

                        // If we're currently on a 'Local Route' we want to ignore any route changes due to
                        // gaggia state.. until the user navigates way from a 'Local Route'
                        if (navController.currentDestination?.route !in LOCAL_ROUTES.entries.map { it.route }) {
                            route = telemetry.currentState?.stateName ?: GaggiaState.NA.stateName

                            // These three states resolve to the same navigation state...
                            if (route in setOf(
                                    GaggiaState.PREINFUSION.stateName,
                                    GaggiaState.BREWING.stateName,
                                    GaggiaState.DONE_BREWING.stateName
                                )
                            ) {
                                route = GaggiaState.BREWING.stateName
                            }

                            if (waitingToChangeFromState != GaggiaState.NA) {
                                if (telemetry.currentState != waitingToChangeFromState) {
                                    // We've finally transitioned out of the state we were waiting to change from...
                                    waitingToChangeFromState = GaggiaState.NA
                                } else {
                                    route = WAITING_ROUTE
                                }
                            }

                            // note that the previous screen will receive the new telemetry that
                            // has caused this transition.. so screens should ignore telemetry
                            // with a state that is different than the one they are intended to handle.
                        }
                    }

                    route?.let {
                        navController.navigate(it) {
                            // Even though we aren't providing user with back functionality, we
                            // do this so the nav system doesn't create a backstack and thus
                            // our screens are 'popped' and we can save state.
                            popUpTo(GaggiaState.PREHEAT.stateName) {
                                saveState = true
                            }
                            // we have to explicitly tell compose nav to restore the state of
                            // composables that use rememberSaveable
                            restoreState = true

                            // only navigate if route has changed.
                            launchSingleTop
                        }
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = GaggiaState.NA.stateName
                ) {
                    mainNavigationGraph(
                        onFirstButtonClick = _onFirstButtonClick,
                        onSecondButtonClick = _onSecondButtonClick,
                        onLocalRouteSelected = { localRoute ->
                            localRouteSelected.value = localRoute.route
                        },
                        onBackButtonClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

val WAITING_ROUTE = "waiting"
fun NavGraphBuilder.mainNavigationGraph(
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    onLocalRouteSelected: (LOCAL_ROUTES) -> Unit
) {
    composable(route = WAITING_ROUTE) {
        WaitingForStateChangeScreen()
    }

    composable(route = GaggiaState.NA.stateName) {
        WelcomeScreen()
    }

    composable(route = GaggiaState.SLEEP.stateName) {
        SleepScreen(onWakeClicked = onFirstButtonClick)
    }

    composable(route = GaggiaState.JOINING_NETWORK.stateName) {
        JoiningNetworkScreen(onIgnoreNetworkClick = onFirstButtonClick)
    }

    composable(route = GaggiaState.IGNORING_NETWORK.stateName) {
        IgnoringNetworkScreen()
    }

    composable(route = GaggiaState.PREHEAT.stateName) {
        LiveTelemetryScreen { telemetry ->
            PreheatScreen(
                telemetry = telemetry,
                onFirstButtonClick = onFirstButtonClick,
                onSecondButtonClick = onSecondButtonClick,
                onSettingsSelected = { onLocalRouteSelected(LOCAL_ROUTES.SETTINGS) },
                onLastBrewSelected = { onLocalRouteSelected(LOCAL_ROUTES.LAST_BREW) }
            )
        }
    }

    composable(route = LOCAL_ROUTES.SETTINGS.route) {
        SettingsScreen(
            onExitClicked = onBackButtonClick
        )
    }

    composable(route = LOCAL_ROUTES.LAST_BREW.route) {
        StoredTelemetryScreen { telemetry ->
            LastBrewScreen(
                telemetry = telemetry,
                onDoneClicked = onBackButtonClick
            )
        }
    }

    composable(route = GaggiaState.MEASURE_BEANS.stateName) {
        LiveTelemetryScreen { telemetry ->
            MeasureBeansScreen(
                telemetry,
                onFirstButtonClick = onFirstButtonClick,
                onSecondButtonClick = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.TARE_CUP_AFTER_MEASURE.stateName) {
        LiveTelemetryScreen { telemetry ->
            TareCupAfterMeasureScreen(
                telemetry = telemetry,
                onFirstButtonClick = onFirstButtonClick,
                onSecondButtonClick = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.HEATING_TO_BREW.stateName) {
        LiveTelemetryScreen { telemetry ->
            HeatingToBrewScreen(
                telemetry,
                onSecondButtonClick = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.BREWING.stateName) {
        LiveTelemetryScreen { telemetry ->
            PreinfusionAndBrewingScreen(
                telemetry = telemetry,
                onReadyClicked = onFirstButtonClick,
                onExitClicked = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.HEATING_TO_STEAM.stateName) {
        StoredTelemetryScreen { storedTelemetry ->
            LiveTelemetryScreen { liveTelemetry ->
                storedTelemetry?.let {
                    HeatingToSteamScreen(
                        // we assume telemetry exists from previous state
                        storedTelemetry = storedTelemetry,
                        liveTelemetry = liveTelemetry,
                        onSecondButtonClick = onSecondButtonClick
                    )
                }
            }
        }
    }

    composable(route = GaggiaState.STEAMING.stateName) {
        StoredTelemetryScreen { telemetry ->
            telemetry?.let {
                SteamingScreen(
                    // we assume telemetry exists from previous state
                    telemetry = telemetry,
                    onDoneSteamingClick = onFirstButtonClick
                )
            }
        }
    }

    composable(route = GaggiaState.CLEAN_GROUP_READY.stateName) {
        CleanGroupReadyScreen(
            onFirstButtonClick = onFirstButtonClick
        )
    }

    composable(route = GaggiaState.CLEAN_GROUP_DONE.stateName) {
        CleanGroupDoneScreen(
            onSecondButtonClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.BACKFLUSH_INSTRUCTION_1.stateName) {
        BackflushInsructions1Screen(
            onReadyClicked = onFirstButtonClick,
            onExitClicked = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.BACKFLUSH_INSTRUCTION_2.stateName) {
        BackflushInsructions2Screen(
            onReadyClicked = onFirstButtonClick,
            onExitClicked = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.BACKFLUSH_CYCLE_1.stateName) {
        LiveTelemetryScreen { telemetry ->
            BackflushCycle1Screen(
                telemetry = telemetry,
                onExitClicked = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.BACKFLUSH_INSTRUCTION_3.stateName) {
        BackflushInsructions3Screen(
            onReadyClicked = onFirstButtonClick,
            onExitClicked = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.BACKFLUSH_CYCLE_2.stateName) {
        LiveTelemetryScreen { telemetry ->
            BackflushCycle2Screen(
                telemetry = telemetry,
                onExitClicked = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.BACKFLUSH_CYCLE_DONE.stateName) {
        BackflushDoneScreen(
            onDoneClicked = onFirstButtonClick
        )
    }

    composable(route = GaggiaState.CLEAN_OPTIONS.stateName) {
        CleanOptionsScreen(
            onBackflushClick = onSecondButtonClick,
            onDescaleClick = onFirstButtonClick
        )
    }

    composable(route = GaggiaState.DESCALE.stateName) {
        DescaleScreen(
            onReadyClick = onFirstButtonClick,
            onExitClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.HEATING_TO_DISPENSE.stateName) {
        LiveTelemetryScreen { telemetry ->
            HeatingToDispenseScreen(
                telemetry = telemetry,
                onExitClick = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.DISPENSE_HOT_WATER.stateName) {
        LiveTelemetryScreen { telemetry ->
            DispensingHotWaterScreen(
                telemetry = telemetry,
                onDoneClick = onFirstButtonClick
            )
        }
    }

    //GaggiaState.COOL_START -> TODO()
    //GaggiaState.COOLING -> TODO()
    //GaggiaState.COOL_DONE -> TODO()
}

enum class LOCAL_ROUTES (val route: String) {
    SETTINGS("settings"),
    LAST_BREW("last_brew")
}