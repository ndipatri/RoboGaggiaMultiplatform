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
import org.koin.compose.viewmodel.koinViewModel
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
import screens.MeasureBeansScreen
import screens.PreheatScreen
import screens.PreinfusionAndBrewingScreen
import screens.SleepScreen
import screens.SteamingScreen
import screens.TareCupAfterMeasureScreen
import screens.TelemetryScreen
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
                val navState = telemetry.currentState ?: GaggiaState.NA

                var waitingToChangeFromState: GaggiaState by remember { mutableStateOf(GaggiaState.NA) }

                val onFirstButtonClick = { viewModel.firstButtonClick() }
                val onSecondButtonClick = { viewModel.secondButtonClick() }

                val _onFirstButtonClick = {
                    waitingToChangeFromState = navState
                    onFirstButtonClick()
                }

                val _onSecondButtonClick = {
                    waitingToChangeFromState = navState
                    onSecondButtonClick()
                }

                // If this is set to true and BLE is enabled via build config,
                // then bluetooth scanning will begin
                if (!viewModel.bluetoothPermissionAcquired && bluetoothPermissionAcquired) {
                    viewModel.bluetoothPermissionAcquired = bluetoothPermissionAcquired
                }

                LaunchedEffect(navState, waitingToChangeFromState) {
                    var route = navState.stateName

                    if (waitingToChangeFromState != GaggiaState.NA) {
                        if (navState != waitingToChangeFromState) {
                            // We've finally transitioned out of the state we were waiting to change from...
                            waitingToChangeFromState = GaggiaState.NA
                        } else {
                            route = WAITING_ROUTE
                        }
                    }

                    // note that the previous screen will receive the new telemetry that
                    // has caused this transition.. so screens should ignore telemetry
                    // with a state that is different than the one they are intended to handle.

                    navController.navigate(route) {
                        // only navigate if route has changed.
                        launchSingleTop
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = GaggiaState.NA.stateName
                ) {
                    mainNavigationGraph(
                        onFirstButtonClick = _onFirstButtonClick,
                        onSecondButtonClick = _onSecondButtonClick
                    )
                }
            }
        }
    }
}

val WAITING_ROUTE = "waiting"
fun NavGraphBuilder.mainNavigationGraph(
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
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
        TelemetryScreen { telemetry ->
            PreheatScreen(
                telemetry = telemetry,
                onFirstButtonClick = onFirstButtonClick,
                onSecondButtonClick = onSecondButtonClick
            )

            "preheatScreen"
        }
    }

    composable(route = GaggiaState.MEASURE_BEANS.stateName) {
        TelemetryScreen { telemetry ->
            MeasureBeansScreen(
                telemetry,
                onFirstButtonClick = onFirstButtonClick,
                onSecondButtonClick = onSecondButtonClick
            )

            "measureBeansScreen"
        }
    }

    composable(route = GaggiaState.TARE_CUP_AFTER_MEASURE.stateName) {
        TelemetryScreen { telemetry ->
            TareCupAfterMeasureScreen(
                telemetry = telemetry,
                onFirstButtonClick = onFirstButtonClick,
                onSecondButtonClick = onSecondButtonClick
            )

            "tareCupAfterMeasureScreen"
        }
    }

    composable(route = GaggiaState.HEATING_TO_BREW.stateName) {
        TelemetryScreen { telemetry ->
            HeatingToBrewScreen(
                telemetry,
                onSecondButtonClick = onSecondButtonClick
            )

            "heatingToBrewScreen"
        }
    }

    composable(route = GaggiaState.PREINFUSION.stateName) {
        TelemetryScreen { telemetry ->
            PreinfusionAndBrewingScreen(
                telemetry,
                onReadyClicked = onFirstButtonClick,
                onExitClicked = onSecondButtonClick
            )

            "preinfusionAndBrewingScreen"
        }
    }

    composable(route = GaggiaState.BREWING.stateName) {
        TelemetryScreen { telemetry ->
            PreinfusionAndBrewingScreen(
                telemetry = telemetry,
                onReadyClicked = onFirstButtonClick,
                onExitClicked = onSecondButtonClick
            )

            "preinfusionAndBrewingScreen"
        }
    }

    composable(route = GaggiaState.DONE_BREWING.stateName) {
        TelemetryScreen { telemetry ->
            PreinfusionAndBrewingScreen(
                telemetry = telemetry,
                onReadyClicked = onFirstButtonClick,
                onExitClicked = onSecondButtonClick
            )

            "preinfusionAndBrewingScreen"
        }
    }

    composable(route = GaggiaState.HEATING_TO_STEAM.stateName) {
        TelemetryScreen { telemetry ->
            HeatingToSteamScreen(
                telemetry = telemetry,
                onSecondButtonClick = onSecondButtonClick
            )

            "heatingToSteamScreen"
        }
    }

    composable(route = GaggiaState.STEAMING.stateName) {
        TelemetryScreen { telemetry ->
            SteamingScreen(
                telemetry = telemetry,
                onDoneSteamingClick = onFirstButtonClick
            )

            "steamingScreen"
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
            onReadyClick = onFirstButtonClick,
            onExitClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.BACKFLUSH_INSTRUCTION_2.stateName) {
        BackflushInsructions2Screen(
            onReadyClick = onFirstButtonClick,
            onExitClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.BACKFLUSH_CYCLE_1.stateName) {
        TelemetryScreen { telemetry ->
            BackflushCycle1Screen(
                telemetry = telemetry,
                onExitClick = onSecondButtonClick
            )

            "backflushCycle1Screen"
        }
    }

    composable(route = GaggiaState.BACKFLUSH_INSTRUCTION_3.stateName) {
        BackflushInsructions3Screen(
            onReadyClick = onFirstButtonClick,
            onExitClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.BACKFLUSH_CYCLE_2.stateName) {
        TelemetryScreen { telemetry ->
            BackflushCycle2Screen(
                telemetry = telemetry,
                onExitClick = onSecondButtonClick
            )

            "backflushCycle2Screen"
        }
    }

    composable(route = GaggiaState.BACKFLUSH_CYCLE_DONE.stateName) {
        BackflushDoneScreen(
            onDoneClick = onFirstButtonClick
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
        TelemetryScreen { telemetry ->
            HeatingToDispenseScreen(
                telemetry = telemetry,
                onExitClick = onSecondButtonClick
            )

            "heatingToDispenseScreen"
        }
    }

    composable(route = GaggiaState.DISPENSE_HOT_WATER.stateName) {
        TelemetryScreen { telemetry ->
            DispensingHotWaterScreen(
                telemetry = telemetry,
                onDoneClick = onFirstButtonClick
            )

            "dispensingHotWaterScreen"
        }
    }

    //GaggiaState.COOL_START -> TODO()
    //GaggiaState.COOLING -> TODO()
    //GaggiaState.COOL_DONE -> TODO()
}
