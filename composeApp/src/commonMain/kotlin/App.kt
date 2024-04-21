import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import screens.DoneBrewingScreen
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
                    when (uiState.currentTelemetryMessage!!.state) {
                        GaggiaState.NA -> WelcomeScreen()

                        GaggiaState.SLEEP -> SleepScreen(
                            onWakeClicked = _onFirstButtonClick
                        )

                        GaggiaState.JOINING_NETWORK -> JoiningNetworkScreen(
                            onIgnoreNetworkClick = _onFirstButtonClick
                        )

                        GaggiaState.IGNORING_NETWORK -> IgnoringNetworkScreen()

                        GaggiaState.PREHEAT -> PreheatScreen(
                            uiState = uiState,
                            onFirstButtonClick = _onFirstButtonClick,
                            onSecondButtonClick = _onSecondButtonClick
                        )

                        GaggiaState.MEASURE_BEANS -> MeasureBeansScreen(
                            uiState = uiState,
                            onFirstButtonClick = _onFirstButtonClick,
                            onSecondButtonClick = _onSecondButtonClick
                        )

                        GaggiaState.TARE_CUP_AFTER_MEASURE -> TareCupAfterMeasureScreen(
                            uiState = uiState,
                            onFirstButtonClick = _onFirstButtonClick,
                            onSecondButtonClick = _onSecondButtonClick
                        )

                        GaggiaState.HEATING_TO_BREW -> HeatingToBrewScreen(
                            uiState = uiState,
                            onSecondButtonClick = _onSecondButtonClick
                        )

                        GaggiaState.PREINFUSION_AND_BREWING -> PreinfusionAndBrewingScreen(
                            uiState = uiState
                        )

                        GaggiaState.DONE_BREWING -> DoneBrewingScreen(
                            uiState = uiState,
                            onReadyClicked = _onFirstButtonClick,
                            onExitClicked = _onSecondButtonClick
                        )

                        GaggiaState.HEATING_TO_STEAM -> HeatingToSteamScreen(
                            uiState = uiState,
                            onSecondButtonClick = _onSecondButtonClick
                        )

                        GaggiaState.STEAMING -> SteamingScreen(
                            uiState = uiState,
                            onDoneSteamingClick = _onFirstButtonClick
                        )

                        GaggiaState.CLEAN_GROUP_READY -> CleanGroupReadyScreen(
                            onFirstButtonClick = _onFirstButtonClick
                        )

                        GaggiaState.CLEAN_GROUP_DONE -> CleanGroupDoneScreen(
                            onSecondButtonClick = _onSecondButtonClick
                        )

                        GaggiaState.BACKFLUSH_INSTRUCTION_1 -> BackflushInsructions1Screen(
                            onReadyClick = _onFirstButtonClick,
                            onExitClick = _onSecondButtonClick
                        )

                        GaggiaState.BACKFLUSH_INSTRUCTION_2 -> BackflushInsructions2Screen(
                            onReadyClick = _onFirstButtonClick,
                            onExitClick = _onSecondButtonClick
                        )

                        GaggiaState.BACKFLUSH_CYCLE_1 -> BackflushCycle1Screen(
                            uiState = uiState,
                            onExitClick = _onSecondButtonClick
                        )

                        GaggiaState.BACKFLUSH_INSTRUCTION_3 -> BackflushInsructions3Screen(
                            onReadyClick = _onFirstButtonClick,
                            onExitClick = _onSecondButtonClick
                        )

                        GaggiaState.BACKFLUSH_CYCLE_2 -> BackflushCycle2Screen(
                            uiState = uiState,
                            onExitClick = _onSecondButtonClick
                        )

                        GaggiaState.BACKFLUSH_CYCLE_DONE -> BackflushDoneScreen(
                            onDoneClick = _onFirstButtonClick
                        )

                        GaggiaState.CLEAN_OPTIONS -> CleanOptionsScreen(
                            onBackflushClick = _onSecondButtonClick,
                            onDescaleClick = _onFirstButtonClick
                        )

                        GaggiaState.DESCALE -> DescaleScreen(
                            onReadyClick = _onFirstButtonClick,
                            onExitClick = _onSecondButtonClick
                        )

                        GaggiaState.HEATING_TO_DISPENSE -> HeatingToDispenseScreen(
                            uiState = uiState,
                            onExitClick = _onSecondButtonClick
                        )

                        GaggiaState.DISPENSE_HOT_WATER -> DispensingHotWaterScreen(
                            uiState = uiState,
                            onDoneClick = _onFirstButtonClick
                        )

                        //GaggiaState.COOL_START -> TODO()
                        //GaggiaState.COOLING -> TODO()
                        //GaggiaState.COOL_DONE -> TODO()

                        //GaggiaState.HEATING_TO_DISPENSE -> TODO()
                        //GaggiaState.DISPENSE_HOT_WATER -> TODO()

                        else -> {}
                    }
                }
        }
    }
}