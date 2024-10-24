package utils

import vms.GaggiaState
import vms.TelemetryMessage
import vms.Weight

fun renderTelemetry(rawTelemetryString: String): List<TelemetryMessage> {
    return rawTelemetryString.split("\n").map { line ->
        line.split(",").let {
            TelemetryMessage(
                state = GaggiaState.byName(it[0]),
                weight = Weight(it[1]),
                pressureBars = it[2],
                dutyCyclePercent = it[3],
                flowRateGPS = it[4],
                brewTempC = it[5],
                shotsUntilBackflush = it[6],
                totalShots = it[7],
                boilerState = it[8],
            )
        }
    }
}

val typicalPreinfusionCycleTelemetryString =
    "preInfusion, 0:34, -1.000000, 40.400000, 0.000000, 105.750000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 105.000000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 104.750000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 104.250000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 104.250000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 103.250000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 103.000000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 101.500000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 100.750000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 100.000000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 0.000000, 40.400000, 0.000000, 100.500000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 1.000000, 40.400000, 0.000000, 101.500000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 1.000000, 40.400000, 0.000000, 104.000000, 2, 2235, 0\n" +
    "preInfusion, 0:34, 1.000000, 40.400000, 0.000000, 106.250000, 2, 2235, 0\n" +
    "preInfusion, 1:34, 2.000000, 40.400000, 0.833333, 109.250000, 2, 2235, 0"

val typicalBrewCycleTelemetryString =
    "brewing, 2:34, 2.000000, 41.266667, 0.833333, 110.250000, 2, 2235, 0\n" +
    "brewing, 4:34, 2.000000, 43.000000, 1.666667, 111.750000, 2, 2235, 0\n" +
    "brewing, 5:34, 2.000000, 43.900000, 0.833333, 111.750000, 2, 2235, 0\n" +
    "brewing, 7:34, 4.000000, 45.800000, 1.666667, 111.000000, 2, 2235, 0\n" +
    "brewing, 9:34, 4.000000, 46.700000, 1.666667, 110.250000, 2, 2235, 0\n" +
    "brewing, 11:34, 4.000000, 47.766667, 1.666667, 109.000000, 2, 2235, 0\n" +
    "brewing, 14:34, 5.000000, 48.833333, 2.500000, 107.500000, 2, 2235, 0\n" +
    "brewing, 16:34, 5.000000, 49.066667, 1.666667, 106.000000, 2, 2235, 0"