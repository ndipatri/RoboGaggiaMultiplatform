package com.ndipatri.robogaggia.previews

import App
import AppContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import screens.JoiningNetworkScreen
import utils.renderTelemetry
import utils.typicalBrewCycleTelemetryString
import vms.GaggiaState
import vms.TelemetryMessage

/**
 *
 * NJD - Preview doesn't work for complicated Composables.. simple things like Text work.. My
 * guess is its the resources.. but not sure...

@Preview()
//@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun PreviewJoiningNetworkScreen() {
    JoiningNetworkScreen(onIgnoreNetworkClick = {})
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun PreviewIgnoringNetworkScreen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "ignoringNetwork, PID(0.200000:1.000000:2.000000), 0, -1.000000, 40.400000, 0.000000, 105.750000"
            )
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun PreviewSleepScreen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "sleep, PID(0.200000:1.000000:2.000000), 0, -1.000000, 40.400000, 0.000000, 105.750000"
            )
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun PreviewPreheatScreen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "preheat,, 55.0, -1.000000, 40.400000, 0.000000, 105.750000/n" +
                "preheat,, 55.0, -1.000000, 40.400000, 0.000000, 105.750000/n" +
                "preheat,, 55.0, -1.000000, 40.400000, 0.000000, 105.750000"),
            previousIsScaleWeighted = true
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun PreviewMeasureBeansScreen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "measureBeans, 19.0, -1.000000, 40.400000, 0.000000, 105.750000/n" +
                        "measureBeans, 19.0, -1.000000, 40.400000, 0.000000, 105.750000/n" +
                        "measureBeans, 19.0, -1.000000, 40.400000, 0.000000, 105.750000"),
            previousIsScaleWeighted = true
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}


@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun PreviewTareCupAfterMeasureScreen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "tareCupAfterMeasure, PID(0.200000:1.000000:2.000000), 0, -1.000000, 40.400000, 0.000000, 105.750000"
            )
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun PreviewHeatingToBrewScreen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "heating, PID(0.200000:1.000000:2.000000), 0, -1.000000, 40.400000, 0.000000, 105.750000"
            )
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun PreviewHeatingToSteamScreen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "heatingToSteam, PID(0.200000:1.000000:2.000000), 0, -1.000000, 40.400000, 0.000000, 105.750000"
            )
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun PreviewSteamingScreen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "steaming, PID(0.200000:1.000000:2.000000), 0, -1.000000, 40.400000, 0.000000, 105.750000"
            )
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}


@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun PreviewDoneBrewingScreen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(typicalBrewCycleTelemetryString)
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun PreviewBrewingScreen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(typicalBrewCycleTelemetryString)
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun BackflushInsructions1Screen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "cleanInst1, 0, -1.000000, 40.400000, 0.000000, 105.750000"
            )
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun BackflushInsructions2Screen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "cleanInst2, 0, -1.000000, 40.400000, 0.000000, 105.750000"
            )
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun BackflushCycle1Screen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "cleanSoap, 0, -1.000000, 40.400000, 0.000000, 105.750000"
            )
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun BackflushInsructions3Screen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "cleanInst3, 0, -1.000000, 40.400000, 0.000000, 105.750000"
            )
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun BackflushCycle2Screen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "cleanRinse, 0, -1.000000, 40.400000, 0.000000, 105.750000"
            )
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}

@Preview(device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360)
@Composable
fun BackflushDoneScreen() {
    AppContent(
        uiState = UIState(
            telemetry = renderTelemetry(
                "cleanDone, 0, -1.000000, 40.400000, 0.000000, 105.750000"
            )
        ),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}


**/
