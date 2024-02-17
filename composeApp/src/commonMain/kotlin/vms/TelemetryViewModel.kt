package vms

import MQTTClient
import com.ndipatri.robogaggia.BuildKonfig
import currentTimeMillis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mqtt.MQTTVersion
import mqtt.Subscription
import mqtt.packets.Qos
import mqtt.packets.mqttv5.SubscriptionOptions
import kotlin.math.abs


class TelemetryViewModel: CoroutineViewModel() {

    lateinit var client: MQTTClient

    var clientId = ""

    // This UIState will have empty Telemetry until we start receiving messages from MQTT.
    //
    // If we're within brew cycle (Preinfusion or Brewing), we accumulate only those values,
    // otherwise we just accumulate last TELEMETRY_WINDOW_SIZE values...
    private val UI_STATE_FLOW_MAX_AGE_MILLIS = 30000L
    private var uiStateFlowLastUpdatedTimeMillis: Long = -1L
    val uiStateFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState())

    init {
        startClientAndSubscribeToTelemetryTopic(500)

        if (BuildKonfig.USE_GAGGIA_SIMULATOR.toBooleanStrict()) {
            GaggiaSimulator(coroutineScope)
        }
    }

    fun firstButtonClick() {
        sendCommand(CommandType.FIRST_BUTTON_CLICK)
    }

    fun secondButtonClick() {
        sendCommand(CommandType.SECOND_BUTTON_CLICK)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    fun startClientAndSubscribeToTelemetryTopic(startDelayMillis: Long) {

        coroutineScope.launch(Dispatchers.Default) {
            while (true) {

                delay(startDelayMillis)

                clientId += currentTimeMillis()

                client = MQTTClient(
                    mqttVersion = MQTTVersion.MQTT3_1_1,
                    clientId = clientId,
                    address = BuildKonfig.MQTT_SERVER_ADDRESS,
                    port = 1883,
                    tls = null,
                    userName = BuildKonfig.AIO_USERNAME,
                    password = BuildKonfig.AIO_PASSWORD.encodeToByteArray().toUByteArray(),
                ) { mqttPublish ->
                    mqttPublish.payload?.let {
                        val message = it.toByteArray().decodeToString()
                        handleMessage(message)
                    }
                }

                try {
                    client.subscribe(
                        listOf(
                            Subscription(
                                telemetryTopic,
                                SubscriptionOptions(Qos.AT_MOST_ONCE)
                            )
                        )
                    )
                    println("*** VM connecting to MQTT broker.")
                    client.run() // Blocking method, use step() if you don't want to block the thread

                } catch (ex: Exception) {
                    ex.printStackTrace()
                    println("*** VM failed to connect to MQTT broker: $ex")
                }
            }
        }

        coroutineScope.launch(Dispatchers.Default) {
            while (true) {

                delay(5000)

                // if time expired
                if (uiStateFlowLastUpdatedTimeMillis > 0 && (currentTimeMillis() - uiStateFlowLastUpdatedTimeMillis > UI_STATE_FLOW_MAX_AGE_MILLIS)) {

                    // and not in sleep state (obviously in sleep state we aren't getting updates)
                    if (uiStateFlow.value.currentTelemetryMessage?.state != GaggiaState.SLEEP) {
                        println("*** VM: UIStateFlow is too old.. using unknown state as heartbeat.")
                        uiStateFlow.emit(UIState())
                    }
                }
            }
        }
    }

    private fun handleMessage(message: String) {
        println("*** VM: Incoming message: ${message}")

        lateinit var state: GaggiaState
        lateinit var measuredWeightGrams: String
        lateinit var measuredPressureBars: String
        lateinit var pumpDutyCycle: String
        lateinit var flowRateGPS: String
        lateinit var brewTempC: String

        message.split(",").forEachIndexed() { index, element ->
            when (index) {
                0 -> state = GaggiaState.byName(element)
                1 -> measuredWeightGrams = element
                2 -> measuredPressureBars = element
                3 -> pumpDutyCycle = element
                4 -> flowRateGPS = element
                5 -> brewTempC = element
            }
        }

        val newTelemetry = TelemetryMessage(
            state = state,
            weightGrams = measuredWeightGrams,
            pressureBars = measuredPressureBars,
            dutyCyclePercent = pumpDutyCycle,
            flowRateGPS = flowRateGPS,
            brewTempC = brewTempC,
        )

        // If we're within brew cycle (Preinfusion or Brewing), we accumulate only those values,
        // otherwise we just accumulate last TELEMETRY_WINDOW_SIZE values...

        coroutineScope.launch {
            val newAccumulatedTelemetry = mutableListOf<TelemetryMessage>()

            val existingValues = uiStateFlow.value.telemetry
            val lastState = if (existingValues.isEmpty()) null else existingValues.last().state

            if (state in setOf(GaggiaState.PREINFUSION_AND_BREWING, GaggiaState.DONE_BREWING)) {
                newAccumulatedTelemetry.addAll(existingValues)
            } else {
                // keep last TELEMETRY_WINDOW_SIZE-1 values  (we're about to add one more)
                if (existingValues.isNotEmpty()) {
                    existingValues[existingValues.size-1].let {
                        newAccumulatedTelemetry.add(it)
                    }
                }
            }
            newAccumulatedTelemetry.add(newTelemetry)

            uiStateFlow.emit(
                uiStateFlow.value.copy(
                    telemetry = newAccumulatedTelemetry,
                    previousIsScaleWeighted = uiStateFlow.value.isScaleWeighted ?: false,
                ).also {
                    println("*** VM: currentTelemetryMessage: ${it.currentTelemetryMessage}")
                    println("*** VM: previousTelemetryMessage: ${it.previousTelemetryMessage}")
                    println("*** VM: isScaleSettled: ${it.isScaleSettled}")
                    println("*** VM: isScaleWeighted: ${it.isScaleWeighted}")
                    println("*** VM: isCupOnlyOnScale: ${it.isCupOnlyOnScale}")
                }
            )

            // Keep track of this for keep-alive reasons.
            uiStateFlowLastUpdatedTimeMillis = currentTimeMillis()
        }
    }

    companion object {
        const val telemetryTopic = "ndipatri/feeds/robogaggiatelemetry"
        const val commandTopic = "ndipatri/feeds/robogaggiacommand"
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    private fun sendCommand(commandType: CommandType) {
        coroutineScope.launch(Dispatchers.Default) {
            client.publish(
                retain = false,
                qos = Qos.AT_MOST_ONCE,
                topic = commandTopic,
                payload = commandType.transmitName.encodeToByteArray().toUByteArray()
            )
        }
    }
}

enum class CommandType(val transmitName: String) {
    FIRST_BUTTON_CLICK("short"),
    SECOND_BUTTON_CLICK("long");

    companion object {
        fun byTransmitName(transmitName: String): CommandType {
            values().forEach {
                if (it.transmitName == transmitName) {
                    return it
                }
            }
            return FIRST_BUTTON_CLICK
        }
    }
}

data class TelemetryMessage(
    val state: GaggiaState,
    val weightGrams: String,
    val pressureBars: String,
    val dutyCyclePercent: String,
    val flowRateGPS: String,
    val brewTempC: String
)

enum class GaggiaState(val stateName: String) {
    IGNORING_NETWORK("ignoringNetwork"),
    JOINING_NETWORK("joiningNetwork"),
    SLEEP("sleep"),
    PREHEAT("preheat"),
    MEASURE_BEANS("measureBeans"),
    TARE_CUP_AFTER_MEASURE("tareCupAfterMeasure"),
    HEATING_TO_BREW("heating"),

    // We consolidate the 'preInfusion' and 'brewing' states in this
    // mobile app.. On the Gaggia, they are separate..
    // We use the 'byName' function to do the fusing.
    PREINFUSION_AND_BREWING("preInfusionAndBrewing"),

    DONE_BREWING("doneBrewing"),
    HEATING_TO_STEAM("heatingToSteam"),
    STEAMING("steaming"),
    CLEAN_GROUP_READY("cleanGroupReady"),
    CLEAN_GROUP_DONE("cleanGroupDone"),

    CLEAN_OPTIONS("cleanOptions"),
    DESCALE("descale"),
    COOL_START("coolStart"),
    COOLING("cooling"),
    COOL_DONE("coolDone"),
    BACKFLUSH_INSTRUCTION_1("cleanInst1"),
    BACKFLUSH_INSTRUCTION_2("cleanInst2"),
    BACKFLUSH_CYCLE_1("cleanSoap"),
    BACKFLUSH_INSTRUCTION_3("cleanInst3"),
    BACKFLUSH_CYCLE_2("cleanRinse"),
    BACKFLUSH_CYCLE_DONE("cleanDone"),
    HEATING_TO_DISPENSE("heatingToDispense"),
    DISPENSE_HOT_WATER("dispenseHotWater"),
    NA("na");

    companion object {
        fun byName(name: String): GaggiaState {

            // exception logic to fuse together two gaggia states
            // into one mobile state
            if (name in setOf("preInfusion", "brewing")) {
                return GaggiaState.PREINFUSION_AND_BREWING
            }

            for (candidate in GaggiaState.values()) {

                if (candidate.stateName == name) {
                    return candidate
                }
            }

            return NA
        }
    }
}


// These are upper and lower values for a classic Schmitt Trigger to prevent bouncing
// when determining if scale is weighted ...

// The treshold nees to be a big higher/lower than the real value
// to allow for the trigger
val WEIGHT_OF_EMPTY_SCALE_GRAMS = 5F
val SCHMITT_TRIGGER_THRESHOLD = 1.1F
fun lowerSettleWeightThresholdGrams(state: GaggiaState): Float? {
    return when (state) {
        GaggiaState.PREHEAT -> {
            // PREHEAT weight is NOT tared... So the weight we are
            // getting includes the weight of the scale itself, which
            // is a known quantity...

            // below this, and the scale is considered NOT weighted
            WEIGHT_OF_EMPTY_SCALE_GRAMS * SCHMITT_TRIGGER_THRESHOLD
        }

        GaggiaState.MEASURE_BEANS, GaggiaState.TARE_CUP_AFTER_MEASURE -> {
            // weight coming back is already tared with
            // the cup and scale ... so any weight is beans

            // below this, and scale is considered NOT weighted with beans
            8.0F
        }

        else -> {
            return null
        }
    }
}

fun upperSettleWeightThresholdGrams(state: GaggiaState): Float? {
    return when (state) {
        GaggiaState.PREHEAT -> {

            // We pick a weight here that would comfortably be higher
            // that the weight of the scale enough so that it would
            // indicate a cup is now on the scale.
            WEIGHT_OF_EMPTY_SCALE_GRAMS * 2
        }

        GaggiaState.MEASURE_BEANS,  GaggiaState.TARE_CUP_AFTER_MEASURE -> {
            // weight coming back is already tared with
            // the cup and scale ... so any weight is beans
            // above this, and scale is considered weighted with beans            10.0F
            12.0F
        }

        else -> {
            return null
        }
    }
}



data class UIState(
    val telemetry: List<TelemetryMessage> = listOf(),
    val previousIsScaleWeighted: Boolean = false,
) {

    // if something is on the scale
    val isScaleWeighted: Boolean
        get() {
            return isScaleSettled && isScaleWeightedRaw
        }

    // This shows raw weight without waiting for scale to settle.
    val isScaleWeightedRaw: Boolean
        get() {
            val state = telemetry.lastOrNull()?.state ?: return false
            val measuredWeightGrams = telemetry.lastOrNull()?.weightGrams ?: return false

            return (previousIsScaleWeighted &&
                            lowerSettleWeightThresholdGrams(state) != null &&
                            measuredWeightGrams.trim()
                                .toFloat() >= lowerSettleWeightThresholdGrams(state)!!) ||

                            (!previousIsScaleWeighted &&
                                    upperSettleWeightThresholdGrams(state) != null &&
                                    measuredWeightGrams.trim()
                                        .toFloat() > upperSettleWeightThresholdGrams(state)!!)
        }


    // 'tared' in this case means this weight represents not necessarily
    // everything on the scale, but what is supposed to be measured.. For
    // example, if the scale has a cup filled with beans, the value here is
    // just the weight of the beans.
    val currentTaredWeight: Float?
        get() {
            return if (telemetry.isNotEmpty()) {
                telemetry.last().weightGrams.trim().toFloat()
            } else {
                null
            }
        }

    val currentTemperature: Float?
        get() {
            return if (telemetry.isNotEmpty()) {
                telemetry.last().brewTempC.trim().toFloat()
            } else {
                null
            }
        }

    val currentPressure: Float?
        get() {
            return if (telemetry.isNotEmpty()) {
                telemetry.last().pressureBars.trim().toFloat()
            } else {
                null
            }
        }

    val currentTelemetryMessage: TelemetryMessage?
        get() {
            return if (telemetry.isNotEmpty()) {
                telemetry.last()
            } else {
                null
            }
        }

    val previousTelemetryMessage: TelemetryMessage?
        get() {
            return if (telemetry.size > 1) {
                telemetry.elementAt(telemetry.size - 2)
            } else {
                null
            }
        }

    // For settling, only consider decimal value of weight
    val SETTLED_WEIGHT_THRESHOLD = 3
    val isScaleSettled =
        if (currentTelemetryMessage?.weightGrams != null && previousTelemetryMessage?.weightGrams != null) {
            abs(
                (currentTelemetryMessage!!.weightGrams.trim().toFloat().toInt() -
                        (previousTelemetryMessage!!.weightGrams.trim().toFloat().toInt()))
            ) < SETTLED_WEIGHT_THRESHOLD
        } else {
            false
        }

    // Only valid during TARE_CUP_AFTER_MEASURE state
    val isCupOnlyOnScale: Boolean? =
        if (currentTelemetryMessage?.state == GaggiaState.TARE_CUP_AFTER_MEASURE) {
        isScaleSettled && (currentTelemetryMessage?.weightGrams?.trim()?.toFloat()?.let { abs(it) < 2F } ?: false)
        } else { null }
}