import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.bluefalcon.ApplicationContext
import org.jetbrains.compose.resources.ExperimentalResourceApi
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
import vms.GaggiaState
import vms.TelemetryViewModel
import vms.UIState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(context: ApplicationContext, bluetoothPermissionAcquired: Boolean) {

    // NJD TODO - need to use KOIN for this eventually
    val viewModel = remember { TelemetryViewModel(context) }

    // If this is set to true and BLE is enabled via build config,
    // then bluetooth scanning will begin
    if (!viewModel.bluetoothPermissionAcquired && bluetoothPermissionAcquired) {
        viewModel.bluetoothPermissionAcquired = bluetoothPermissionAcquired
    }

    val uiState by viewModel.uiStateFlow.collectAsState()
    val onFirstButtonClick = { viewModel.firstButtonClick() }
    val onSecondButtonClick = { viewModel.secondButtonClick() }

    AppContent(uiState, onFirstButtonClick, onSecondButtonClick)
}

@Composable
fun AppContent(
    uiState: UIState,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
) {
    val navController: NavHostController = rememberNavController()

    var waitingToChangeFromState: GaggiaState by remember { mutableStateOf(GaggiaState.NA) }

    val _onFirstButtonClick = {
        waitingToChangeFromState = uiState.currentTelemetryMessage?.state ?: GaggiaState.NA
        onFirstButtonClick()
    }

    val _onSecondButtonClick = {
        waitingToChangeFromState = uiState.currentTelemetryMessage?.state ?: GaggiaState.NA
        onSecondButtonClick()
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {

        RoboGaggiaTheme(darkTheme = true) {

            if (waitingToChangeFromState != GaggiaState.NA) {
                WaitingForStateChangeScreen()

                // We've finally transitioned out of the state we were waiting to change from...
                if (uiState.currentTelemetryMessage?.state != waitingToChangeFromState) {
                    waitingToChangeFromState = GaggiaState.NA
                }
            } else
                if (uiState.telemetry.isEmpty()) {
                    WelcomeScreen()
                } else {

                    // If there is more than one telemetry message, the most recent one
                    // is the one we use to determine current state...
                    val currentGaggiaState = uiState.currentTelemetryMessage!!.state.stateName
                    LaunchedEffect(currentGaggiaState) {
                        navController.navigate(currentGaggiaState)
                    }

                    NavHost(
                        navController = navController,
                        startDestination = GaggiaState.NA.stateName
                    ) {
                        mainNavigationGraph(
                            uiState = uiState,
                            onFirstButtonClick = _onFirstButtonClick,
                            onSecondButtonClick = _onSecondButtonClick
                        )
                    }
                }
        }
    }
}

fun NavGraphBuilder.mainNavigationGraph(
    uiState: UIState,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
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
        PreheatScreen(
            uiState = uiState,
            onFirstButtonClick = onFirstButtonClick,
            onSecondButtonClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.MEASURE_BEANS.stateName) {
        MeasureBeansScreen(
            uiState = uiState,
            onFirstButtonClick = onFirstButtonClick,
            onSecondButtonClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.TARE_CUP_AFTER_MEASURE.stateName) {
        TareCupAfterMeasureScreen(
            uiState = uiState,
            onFirstButtonClick = onFirstButtonClick,
            onSecondButtonClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.HEATING_TO_BREW.stateName) {
        HeatingToBrewScreen(
            uiState = uiState,
            onSecondButtonClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.PREINFUSION.stateName) {
        PreinfusionAndBrewingScreen(
            uiState = uiState,
            onReadyClicked = onFirstButtonClick,
            onExitClicked = onSecondButtonClick
        )
    }
    composable(route = GaggiaState.BREWING.stateName) {
        PreinfusionAndBrewingScreen(
            uiState = uiState,
            onReadyClicked = onFirstButtonClick,
            onExitClicked = onSecondButtonClick
        )
    }
    composable(route = GaggiaState.DONE_BREWING.stateName) {
        PreinfusionAndBrewingScreen(
            uiState = uiState,
            onReadyClicked = onFirstButtonClick,
            onExitClicked = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.HEATING_TO_STEAM.stateName) {
        HeatingToSteamScreen(
            uiState = uiState,
            onSecondButtonClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.STEAMING.stateName) {
        SteamingScreen(
            uiState = uiState,
            onDoneSteamingClick = onFirstButtonClick
        )
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
        BackflushCycle1Screen(
            uiState = uiState,
            onExitClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.BACKFLUSH_INSTRUCTION_3.stateName) {
        BackflushInsructions3Screen(
            onReadyClick = onFirstButtonClick,
            onExitClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.BACKFLUSH_CYCLE_2.stateName) {
        BackflushCycle2Screen(
            uiState = uiState,
            onExitClick = onSecondButtonClick
        )
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
        HeatingToDispenseScreen(
            uiState = uiState,
            onExitClick = onSecondButtonClick
        )
    }

    composable(route = GaggiaState.DISPENSE_HOT_WATER.stateName) {
        DispensingHotWaterScreen(
            uiState = uiState,
            onDoneClick = onFirstButtonClick
        )
    }

    //GaggiaState.COOL_START -> TODO()
    //GaggiaState.COOLING -> TODO()
    //GaggiaState.COOL_DONE -> TODO()

    //GaggiaState.HEATING_TO_DISPENSE -> TODO()
    //GaggiaState.DISPENSE_HOT_WATER -> TODO()
}
