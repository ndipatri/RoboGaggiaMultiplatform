package com.ndipatri.robogaggia.previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.dark_circuitboard
import screens.BackflushCycle1Screen
import screens.BackflushCycle2Screen
import screens.BackflushDoneScreen
import screens.BackflushInsructions1Screen
import screens.BackflushInsructions2Screen
import screens.BackflushInsructions3Screen
import screens.HeatingToBrewScreen
import screens.HeatingToSteamScreen
import screens.IgnoringNetworkScreen
import screens.JoiningNetworkScreen
import screens.MeasureBeansScreen
import screens.PreheatScreen
import screens.PreinfusionAndBrewingScreen
import screens.SettingsContent
import screens.SleepScreen
import screens.SteamingScreen
import screens.TareCupAfterMeasureScreen
import services.SettingsViewModel
import theme.RoboGaggiaTheme
import vms.GaggiaState
import vms.Telemetry
import vms.TelemetryMessage
import vms.Temp
import vms.Weight

const val GAGGIA_DEVICE = "spec:width=1434dp,height=662dp,orientation=landscape"

// stateName, weight, pressure, pumpDutyCycle, flowRate, brewTemp, shotsUntilBackflush, totalShots, boilerState
val BASE_TELEMETRY_STRING =
"""
preheat, 0.0:0.0, 3.0, 55.0, 0.000000, 40.400000, 2, 2000, 1
"""

fun String.toTelemetry() =
    Telemetry(
        this.split("\n").filter { it.isNotEmpty() }.map { line ->
            line.split(",").let {
                TelemetryMessage(
                    state = GaggiaState.byName(it[0]),
                    weight = Weight(it[1]),
                    pressureBars = it[2],
                    dutyCyclePercent = it[3],
                    flowRateGPS = it[4],
                    brewTempC = Temp(it[5]),
                    shotsUntilBackflush = it[6],
                    totalShots = it[7],
                    boilerState = it[8],
                )
            }
        }
    )


@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewJoiningNetworkScreen() {
    JoiningNetworkScreen(onIgnoreNetworkClick = {})
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewIgnoringNetworkScreen() {
    IgnoringNetworkScreen()
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewSleepScreen() {
    SleepScreen(
        onWakeClicked = {}
    )
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewPreheatScreen() {
    PreheatScreen(
        telemetry = BASE_TELEMETRY_STRING.toTelemetry(),
        onFirstButtonClick = {},
        onSecondButtonClick = {},
        onSettingsSelected = {}
    )
}


@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewMeasureBeansScreen() {
    MeasureBeansScreen(
        telemetry = BASE_TELEMETRY_STRING.toTelemetry(),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}


@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewTareCupAfterMeasureScreen() {
    TareCupAfterMeasureScreen (
        telemetry = BASE_TELEMETRY_STRING.toTelemetry(),
        onFirstButtonClick = {},
        onSecondButtonClick = {}
    )
}


@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewHeatingToBrewScreen() {
    HeatingToBrewScreen (
        telemetry = BASE_TELEMETRY_STRING.toTelemetry(),
        onSecondButtonClick = {}
    )
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewHeatingToSteamScreen() {
    HeatingToSteamScreen (
        telemetry = BASE_TELEMETRY_STRING.toTelemetry(),
        onSecondButtonClick = {}
    )
}


@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewSteamingScreen() {
    SteamingScreen (
        telemetry = BASE_TELEMETRY_STRING.toTelemetry(),
        onDoneSteamingClick = {}
    )
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewDoneBrewingScreen() {
    PreinfusionAndBrewingScreen (
        telemetry = BASE_TELEMETRY_STRING.toTelemetry(),
        onReadyClicked = {},
        onExitClicked = {}
    )
}

@Preview(device = GAGGIA_DEVICE)
@Composable
// NOTE: Run in Interactive mode to see 'Shot Clock'
fun PreviewBrewingScreen() {
    PreinfusionAndBrewingScreen(
        telemetry =
"""
preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 105.000000, 2, 2235, 0
preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 104.750000, 2, 2235, 0
preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 104.250000, 2, 2235, 0
preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 104.250000, 2, 2235, 0
preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 103.250000, 2, 2235, 0
preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 103.000000, 2, 2235, 0
preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 101.500000, 2, 2235, 0
preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 100.750000, 2, 2235, 0
preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 100.000000, 2, 2235, 0
preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 100.500000, 2, 2235, 0
preInfusion, 0:34, 1.000000, 40.400000, 0.000000, 101.500000, 2, 2235, 0
preInfusion, 0:34, 1.000000, 40.400000, 0.000000, 104.000000, 2, 2235, 0
preInfusion, 0:34, 1.000000, 40.400000, 0.000000, 106.250000, 2, 2235, 0
preInfusion, 1:34, 2.000000, 40.400000, 0.833333, 109.250000, 2, 2235, 0
brewing, 2:34, 2.000000, 41.266667, 0.833333, 110.250000, 2, 2235, 0
brewing, 4:34, 2.000000, 43.000000, 1.666667, 111.750000, 2, 2235, 0
brewing, 5:34, 2.000000, 43.900000, 0.833333, 111.750000, 2, 2235, 0
brewing, 7:34, 4.000000, 45.800000, 1.666667, 111.000000, 2, 2235, 0
brewing, 9:34, 4.000000, 46.700000, 1.666667, 110.250000, 2, 2235, 0
brewing, 11:34, 4.000000, 47.766667, 1.666667, 109.000000, 2, 2235, 0
brewing, 14:34, 5.000000, 48.833333, 2.500000, 107.500000, 2, 2235, 0
brewing, 16:34, 5.000000, 49.066667, 1.666667, 106.000000, 2, 2235, 0
""".toTelemetry(),
        onReadyClicked = {},
        onExitClicked = {}
    )
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewBackflushInsructions1Screen() {
    BackflushInsructions1Screen(
        onReadyClicked = {},
        onExitClicked = {}
    )
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewBackflushInsructions2Screen() {
    BackflushInsructions2Screen(
        onReadyClicked = {},
        onExitClicked = {}
    )
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewBackflushInsructions3Screen() {
    BackflushInsructions3Screen(
        onReadyClicked = {},
        onExitClicked = {}
    )
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewBackflushCycle1Screen() {
    BackflushCycle1Screen (
// notice weight is pass and pressure is total number of passes
        telemetry =
"""
cleanSoap, 2, 10, 49.066667, 1.666667, 106.000000, 2, 2235, 0
""".toTelemetry(),
        onExitClicked = {}
    )
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewBackflushCycle2Screen() {
    BackflushCycle2Screen(
// notice weight is pass and pressure is total number of passes
        telemetry =
"""
cleanSoap, 2, 10, 49.066667, 1.666667, 106.000000, 2, 2235, 0
""".toTelemetry(),
        onExitClicked = {}
    )
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewBackflushDoneScreen() {
    BackflushDoneScreen(
        onDoneClicked = {}
    )
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewSettingsScreen_Success() {
    RoboGaggiaTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(Res.drawable.dark_circuitboard),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            SettingsContent(
                settings = SettingsViewModel.SettingsState(10, SettingsViewModel.SubmissionState.Success),
                onExitClicked = {},
                onSettingsSave = {}
            )
        }
    }
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewSettingsScreen_Loading() {
    RoboGaggiaTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(Res.drawable.dark_circuitboard),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            SettingsContent(
                settings = SettingsViewModel.SettingsState(10, SettingsViewModel.SubmissionState.Loading),
                onExitClicked = {},
                onSettingsSave = {}
            )
        }
    }
}

@Preview(device = GAGGIA_DEVICE)
@Composable
fun PreviewSettingsScreen_Error() {
    RoboGaggiaTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(Res.drawable.dark_circuitboard),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            SettingsContent(
                settings = SettingsViewModel.SettingsState(10, SettingsViewModel.SubmissionState.Error),
                onExitClicked = {},
                onSettingsSave = {}
            )
        }
    }
}
