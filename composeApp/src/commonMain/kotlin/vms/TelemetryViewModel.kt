package vms

import AdvertisementDataRetrievalKeys
import MQTTClient
import com.ndipatri.robogaggia.BuildKonfig
import currentTimeMillis
import dev.bluefalcon.ApplicationContext
import dev.bluefalcon.BlueFalcon
import dev.bluefalcon.BlueFalconDelegate
import dev.bluefalcon.BluetoothCharacteristic
import dev.bluefalcon.BluetoothCharacteristicDescriptor
import dev.bluefalcon.BluetoothPeripheral
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mqtt.MQTTVersion
import mqtt.Subscription
import mqtt.packets.Qos
import mqtt.packets.mqttv5.SubscriptionOptions
import kotlin.math.abs

class TelemetryViewModel(val context: ApplicationContext) : CoroutineViewModel() {

    lateinit var client: MQTTClient

    lateinit var blueFalcon: BlueFalcon

    // I don't trust the blueFalcon 'isScanning' property, so I
    // track this myself...if the scan() call throws an exception,
    // the blueFalcon.isScanning will erroneously stay true.
    private var bluetoothIsScanning = false

    var connectedBLEPeripheral: BluetoothPeripheral? = null
    var gaggiaReceiveBLECharacteristic: BluetoothCharacteristic? = null

    var bluetoothPermissionAcquired: Boolean = false
        set(value) {
            field = value
            if (value) {
                if (BuildKonfig.USE_BLE.toBooleanStrict()) {
                    blueFalcon = BlueFalcon(context, GAGGIA_UART_BLE_SERVICE_UUID).apply {
                        delegates.add(bluetoothListener)
                    }

                    scanForBluetooth()
                }
            }
        }

    var clientId = ""

    // This UIState will have empty Telemetry until we start receiving messages from MQTT.
    //
    // If we're within brew cycle (Preinfusion or Brewing), we accumulate only those values,
    // otherwise we just accumulate last TELEMETRY_WINDOW_SIZE values...
    private val UI_STATE_FLOW_MAX_AGE_MILLIS = 3000L
    private var uiStateFlowLastUpdatedTimeMillis: Long = -1L
    val uiStateFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState())

    init {
        if (!BuildKonfig.USE_BLE.toBooleanStrict()) {
            startMQTTClientAndSubscribeToTelemetryTopic(500)

            if (BuildKonfig.USE_GAGGIA_SIMULATOR.toBooleanStrict()) {
                if (BuildKonfig.USE_GAGGIA_SIMULATOR.toBooleanStrict()) {
                    GaggiaSimulator(coroutineScope)
                }
            }
        }

        checkForStaleTelemetry()
    }

    fun firstButtonClick() {
        buttonClick(CommandType.FIRST_BUTTON_CLICK)
    }

    fun secondButtonClick() {
        buttonClick(CommandType.SECOND_BUTTON_CLICK)
    }

    // Sometimes the local bluetooth hardware will cause the
    // scan() call from blueFalcon to throw an exception... you
    // have to keep retrying until this doesn't happen.
    // https://github.com/Reedyuk/blue-falcon/issues/26
    private fun scanForBluetooth() {
        coroutineScope.launch(Dispatchers.Default) {
            while (!bluetoothIsScanning) {
                try {
                    println("*** NJD: starting scan...")
                    blueFalcon.scan()
                    bluetoothIsScanning = true
                } catch (th: Throwable) {
                    println("*** NJD: failed to scan for BLE devices: $th")
                    th.printStackTrace()
                    bluetoothIsScanning = false
                }

                delay(2000)
            }
        }
    }

    private fun buttonClick(commandType: CommandType) {
        if (BuildKonfig.USE_BLE.toBooleanStrict()) {
            connectedBLEPeripheral?.let {
                sendBLECommand(commandType)
            }
        } else {
            sendMQTTCommand(commandType)
        }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    fun startMQTTClientAndSubscribeToTelemetryTopic(startDelayMillis: Long) {

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
                    while (client.running) {
                        // this keep the client alive and listening
                        client.step()
                        delay(250)
                    }

                } catch (ex: Exception) {
                    ex.printStackTrace()
                    println("*** VM failed to connect to MQTT broker: $ex")
                }
            }
        }
    }

    private fun checkForStaleTelemetry() {
        coroutineScope.launch(Dispatchers.Default) {
            while (true) {

                delay(5000)

                // if time expired
                if (uiStateFlowLastUpdatedTimeMillis > 0 && (currentTimeMillis() - uiStateFlowLastUpdatedTimeMillis > UI_STATE_FLOW_MAX_AGE_MILLIS)) {

                    // and not in sleep state (obviously in sleep state we aren't getting updates)
                    if (uiStateFlow.value.currentTelemetryMessage?.state != GaggiaState.SLEEP) {
                        uiStateFlow.emit(UIState()) // will foward to 'unknown' state until we receive new telemetry
                    }
                }
            }
        }
    }

    private fun handleMessage(message: String) {
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

            if (state in setOf(GaggiaState.PREINFUSION_AND_BREWING, GaggiaState.DONE_BREWING)) {
                newAccumulatedTelemetry.addAll(existingValues)
            } else {
                // keep last TELEMETRY_WINDOW_SIZE-1 values  (we're about to add one more)
                if (existingValues.isNotEmpty()) {
                    existingValues[existingValues.size - 1].let {
                        newAccumulatedTelemetry.add(it)
                    }
                }
            }
            newAccumulatedTelemetry.add(newTelemetry)

            uiStateFlow.emit(
                uiStateFlow.value.copy(
                    telemetry = newAccumulatedTelemetry,
                    previousIsScaleWeighted = uiStateFlow.value.isScaleWeighted ?: false,
                )
            )

            // Keep track of this for keep-alive reasons.
            uiStateFlowLastUpdatedTimeMillis = currentTimeMillis()
        }
    }

    companion object {
        const val telemetryTopic = "ndipatri/feeds/robogaggiatelemetry"
        const val commandTopic = "ndipatri/feeds/robogaggiacommand"
        const val GAGGIA_UART_BLE_SERVICE_UUID = "6E400001-B5A3-F393-E0A9-E50E24DCCA9E"
        const val GAGGIA_UART_BLE_RX_CHARACTERISTIC = "6E400002-B5A3-F393-E0A9-E50E24DCCA9E"
        const val GAGGIA_UART_BLE_TX_CHARACTERISTIC = "6E400003-B5A3-F393-E0A9-E50E24DCCA9E"
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    private fun sendMQTTCommand(commandType: CommandType) {
        coroutineScope.launch(Dispatchers.Default) {
            client.publish(
                retain = false,
                qos = Qos.AT_MOST_ONCE,
                topic = commandTopic,
                payload = commandType.transmitName.encodeToByteArray().toUByteArray()
            )
            client.step()
        }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    private fun sendBLECommand(commandType: CommandType) {
        connectedBLEPeripheral?.let { peripheral ->
            gaggiaReceiveBLECharacteristic?.let {characteristic ->
                blueFalcon.writeCharacteristic(
                    peripheral, // We wouldn't be sending this command if we weren't connected.
                    characteristic,
                    commandType.transmitName,
                    0x01  // write without response
                )
            }
        }
    }

    val bluetoothListener = object: BlueFalconDelegate {
        override fun didCharacteristcValueChanged(
            bluetoothPeripheral: BluetoothPeripheral,
            bluetoothCharacteristic: BluetoothCharacteristic
        ) {
            bluetoothCharacteristic.value?.let { incomingBytes ->
                val characteristicValue = incomingBytes.decodeToString()

                handleMessage(characteristicValue)
            }
        }

        override fun didConnect(bluetoothPeripheral: BluetoothPeripheral) {
            connectedBLEPeripheral = bluetoothPeripheral
        }

        override fun didDisconnect(bluetoothPeripheral: BluetoothPeripheral) {
            // TODO - Do nothing..
        }

        override fun didDiscoverCharacteristics(bluetoothPeripheral: BluetoothPeripheral) {
            bluetoothPeripheral.services.forEach {
                println("*** NJD: discovered service: ${it.name}!")
            }

            bluetoothPeripheral.services
                .findLast { it.name?.lowercase() == GAGGIA_UART_BLE_SERVICE_UUID.lowercase() }?.characteristics
                    ?.findLast { it.name?.lowercase() == GAGGIA_UART_BLE_TX_CHARACTERISTIC.lowercase() }
                        ?.let { gaggiaTransmitterCharacteristic ->
                            blueFalcon.notifyCharacteristic(
                                bluetoothPeripheral,
                                gaggiaTransmitterCharacteristic,
                                notify = true
                            )
                        }

            bluetoothPeripheral.services
                .findLast { it.name?.lowercase() == GAGGIA_UART_BLE_SERVICE_UUID.lowercase() }?.characteristics
                    ?.findLast { it.name?.lowercase() == GAGGIA_UART_BLE_RX_CHARACTERISTIC.lowercase() }
                        ?.let { characteristic ->
                            gaggiaReceiveBLECharacteristic = characteristic
                        }
       }

        override fun didDiscoverDevice(
            bluetoothPeripheral: BluetoothPeripheral,
            advertisementData: Map<AdvertisementDataRetrievalKeys, Any>
        ) {

            // We always want to immediately connect to the Gaggia.
            println("*** NJD: trying to connect!")
            blueFalcon.connect(bluetoothPeripheral, true)
        }

        override fun didDiscoverServices(bluetoothPeripheral: BluetoothPeripheral) {
            // NJD TODO - do nothing
        }

        override fun didReadDescriptor(
            bluetoothPeripheral: BluetoothPeripheral,
            bluetoothCharacteristicDescriptor: BluetoothCharacteristicDescriptor
        ) {
            // NJD TODO - do nothing
        }

        override fun didRssiUpdate(bluetoothPeripheral: BluetoothPeripheral) {
            // NJD TODO - do nothing
        }

        override fun didUpdateMTU(bluetoothPeripheral: BluetoothPeripheral) {
            // NJD TODO - do nothing
        }

        override fun didWriteCharacteristic(
            bluetoothPeripheral: BluetoothPeripheral,
            bluetoothCharacteristic: BluetoothCharacteristic,
            success: Boolean
        ) {
            // NJD TODO - do nothing
        }

        override fun didWriteDescriptor(
            bluetoothPeripheral: BluetoothPeripheral,
            bluetoothCharacteristicDescriptor: BluetoothCharacteristicDescriptor
        ) {
            println("*** NJD: Gaggia did write characteristic!")
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

        GaggiaState.MEASURE_BEANS, GaggiaState.TARE_CUP_AFTER_MEASURE -> {
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
            isScaleSettled && (currentTelemetryMessage?.weightGrams?.trim()?.toFloat()
                ?.let { abs(it) < 2F } ?: false)
        } else {
            null
        }
}
