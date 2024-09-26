import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionErrors
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
import screens.WaitingForStateChangeScreen
import screens.WelcomeScreen
import theme.RoboGaggiaTheme
import vms.CommandType
import vms.GaggiaState
import vms.Telemetry
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
                val viewModel = koinViewModel<TelemetryViewModel>()

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
                        telemetry = telemetry,
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
    telemetry: Telemetry,
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
        if (telemetry.currentState == GaggiaState.PREHEAT) {
            PreheatScreen(
                telemetry = telemetry,
                onFirstButtonClick = onFirstButtonClick,
                onSecondButtonClick = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.MEASURE_BEANS.stateName) {
        if (telemetry.currentState == GaggiaState.MEASURE_BEANS) {
            MeasureBeansScreen(
                telemetry = telemetry,
                onFirstButtonClick = onFirstButtonClick,
                onSecondButtonClick = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.TARE_CUP_AFTER_MEASURE.stateName) {
        if (telemetry.currentState == GaggiaState.TARE_CUP_AFTER_MEASURE) {
            TareCupAfterMeasureScreen(
                telemetry = telemetry,
                onFirstButtonClick = onFirstButtonClick,
                onSecondButtonClick = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.HEATING_TO_BREW.stateName) {
        if (telemetry.currentState == GaggiaState.HEATING_TO_BREW) {
            HeatingToBrewScreen(
                telemetry = telemetry,
                onSecondButtonClick = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.PREINFUSION.stateName) {
        if (telemetry.currentState == GaggiaState.PREINFUSION) {
            PreinfusionAndBrewingScreen(
                telemetry = telemetry,
                onReadyClicked = onFirstButtonClick,
                onExitClicked = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.BREWING.stateName) {
        if (telemetry.currentState == GaggiaState.BREWING) {
            PreinfusionAndBrewingScreen(
                telemetry = telemetry,
                onReadyClicked = onFirstButtonClick,
                onExitClicked = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.DONE_BREWING.stateName) {
        if (telemetry.currentState == GaggiaState.DONE_BREWING) {
            PreinfusionAndBrewingScreen(
                telemetry = telemetry,
                onReadyClicked = onFirstButtonClick,
                onExitClicked = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.HEATING_TO_STEAM.stateName) {
        if (telemetry.currentState == GaggiaState.HEATING_TO_STEAM) {
            HeatingToSteamScreen(
                telemetry = telemetry,
                onSecondButtonClick = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.STEAMING.stateName) {
        if (telemetry.currentState == GaggiaState.STEAMING) {
            SteamingScreen(
                telemetry = telemetry,
                onDoneSteamingClick = onFirstButtonClick
            )
        }
    }

    composable(route = GaggiaState.CLEAN_GROUP_READY.stateName) {
        if (telemetry.currentState == GaggiaState.CLEAN_GROUP_READY) {
            CleanGroupReadyScreen(
                onFirstButtonClick = onFirstButtonClick
            )
        }
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
        if (telemetry.currentState == GaggiaState.BACKFLUSH_CYCLE_1) {
            BackflushCycle1Screen(
                telemetry = telemetry,
                onExitClick = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.BACKFLUSH_INSTRUCTION_3.stateName) {
        BackflushInsructions3Screen(
            onReadyClick = onFirstButtonClick,
            onExitClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.BACKFLUSH_CYCLE_2.stateName) {
        if (telemetry.currentState == GaggiaState.BACKFLUSH_CYCLE_2) {
            BackflushCycle2Screen(
                telemetry = telemetry,
                onExitClick = onSecondButtonClick
            )
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
        if (telemetry.currentState == GaggiaState.HEATING_TO_DISPENSE) {
            HeatingToDispenseScreen(
                telemetry = telemetry,
                onExitClick = onSecondButtonClick
            )
        }
    }

    composable(route = GaggiaState.DISPENSE_HOT_WATER.stateName) {
        if (telemetry.currentState == GaggiaState.DISPENSE_HOT_WATER) {
            DispensingHotWaterScreen(
                telemetry = telemetry,
                onDoneClick = onFirstButtonClick
            )
        }
    }

    //GaggiaState.COOL_START -> TODO()
    //GaggiaState.COOLING -> TODO()
    //GaggiaState.COOL_DONE -> TODO()

    //GaggiaState.HEATING_TO_DISPENSE -> TODO()
    //GaggiaState.DISPENSE_HOT_WATER -> TODO()
}
