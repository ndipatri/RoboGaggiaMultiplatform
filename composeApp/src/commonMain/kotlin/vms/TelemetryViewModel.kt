package vms

import AdvertisementDataRetrievalKeys
import MQTTClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import mqtt.MQTTVersion
import mqtt.Subscription
import mqtt.packets.Qos
import mqtt.packets.mqttv5.SubscriptionOptions
import robo.ndipatri.robogaggia.proto_datastore_kmm.TelemetryProtoData
import services.MCPManager
import services.MCPQuery
import kotlin.io.println
import kotlin.math.abs

/**
 * This is a ViewModel that is tied to the context of the entire application.
 */
class TelemetryViewModel(val context: ApplicationContext, val mcpManager: MCPManager) :
    ViewModel() {

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
                if (!BuildKonfig.USE_SIMULATOR.toBooleanStrict()) {
                    blueFalcon = BlueFalcon(context, GAGGIA_UART_BLE_SERVICE_UUID).apply {
                        delegates.add(bluetoothListener)
                    }

                    scanForBluetooth()
                }
            }
        }

    var clientId = ""

    // This UIState will have empty Telemetry until we start receiving messages from MQTT or BLE.
    //
    // If we're within brew cycle (Preinfusion or Brewing), we accumulate all telemetry,
    // otherwise we only keep last and current value
    private val UI_STATE_FLOW_MAX_AGE_MILLIS = 30000L
    private var uiStateFlowLastUpdatedTimeMillis: Long = -1L

    // telemetry updates occurs very often.. every 250ms
    val telemetryFlow = MutableStateFlow(Telemetry())

    val mcpQueryFlow = MutableStateFlow<MCPQuery?>(null)

    init {
        if (BuildKonfig.USE_SIMULATOR.toBooleanStrict()) {
            startMQTTClientAndSubscribeToTelemetryTopic(500)
        }

        // Start MCP server and client
        viewModelScope.launch {
            while (isActive) {
                try {
                    val job = viewModelScope.launch {
                        println("*** NJD: Starting MCP.")

                        // We need to keep this coroutine active otherwise the mcpManager will stop
                        // running its server...
                        mcpQueryFlow.collect { mcpQuery ->
                            mcpQuery?.let {
                                println("*** NJD: collected new mcpQuery...")

                                // NJD TODO - for now ignore speech string from mcpQuery.
                                // eventually, we would condition this text somehow ...

                                mcpManager.executeQuery("Please find me the first name for a user with last name 'Smith'.  Your final answer should not refer to any functions.")
                            }
                        }
                    }

                    // this makes the outer coroutine wait for the inner coroutine to finish .. since
                    // the inner coroutine is collecting indefinitely, this would mean an exception
                    job.join()
                    println("*** NJD: For some reason, the MCP server was stopped... Trying again..")
                } catch (ex: Exception) {
                    println("*** NJD: exception while setting up MCP manager: $ex")
                } finally {
                    delay(1000)
                }
            }
        }
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
        viewModelScope.launch(Dispatchers.Default) {
            while (isActive && !bluetoothIsScanning) {
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
        if (!BuildKonfig.USE_SIMULATOR.toBooleanStrict()) {
            connectedBLEPeripheral?.let {
                sendBLECommand(commandType)
            }
        } else {
            sendMQTTCommand(commandType)
        }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    fun startMQTTClientAndSubscribeToTelemetryTopic(startDelayMillis: Long) {

        viewModelScope.launch(Dispatchers.Default) {
            while (isActive) {

                delay(startDelayMillis)

                clientId += currentTimeMillis()

                client = MQTTClient(
                    mqttVersion = MQTTVersion.MQTT3_1_1,
                    clientId = clientId,
                    address = BuildKonfig.MQTT_SERVER_ADDRESS,
                    port = 1883,
                    tls = null,
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
                    while (isActive && client.running) {
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
        viewModelScope.launch(Dispatchers.Default) {
            while (isActive) {

                delay(5000)

                // if time expired
                if (uiStateFlowLastUpdatedTimeMillis > 0 && (currentTimeMillis() - uiStateFlowLastUpdatedTimeMillis > UI_STATE_FLOW_MAX_AGE_MILLIS)) {

                    // and not in sleep state (obviously in sleep state we aren't getting updates)
                    if (telemetryFlow.value.currentTelemetryMessage?.state != GaggiaState.SLEEP) {
                        telemetryFlow.emit(Telemetry()) // will foward to 'unknown' state until we receive new telemetry
                    }
                }
            }
        }
    }

    private fun handleMessage(message: String) {
        lateinit var state: GaggiaState
        lateinit var measuredWeight: String
        lateinit var measuredPressureBars: String
        lateinit var pumpDutyCycle: String
        lateinit var flowRateGPS: String
        lateinit var brewTempC: String
        lateinit var shotsUntilBackflush: String
        lateinit var totalShots: String
        lateinit var boilerState: String

        message.split(",").forEachIndexed() { index, element ->
            when (index) {
                0 -> state = GaggiaState.byName(element)
                1 -> measuredWeight = element
                2 -> measuredPressureBars = element
                3 -> pumpDutyCycle = element
                4 -> flowRateGPS = element
                5 -> brewTempC = element
                6 -> shotsUntilBackflush = element
                7 -> totalShots = element
                8 -> boilerState = element
            }
        }

        val newTelemetry = TelemetryMessage(
            state = state,
            weight = Weight(measuredWeight),
            pressureBars = measuredPressureBars,
            dutyCyclePercent = pumpDutyCycle,
            flowRateGPS = flowRateGPS,
            brewTempC = Temp(brewTempC),
            shotsUntilBackflush = shotsUntilBackflush,
            totalShots = totalShots,
            boilerState = boilerState
        )
        viewModelScope.launch {
            val newAccumulatedTelemetry = mutableListOf<TelemetryMessage>()

            val existingValues = telemetryFlow.value.telemetry

            // If we're within brew cycle (Preinfusion or Brewing), we accumulate all telemetry,
            // otherwise we only keep last and current value
            if (state in setOf(GaggiaState.PREINFUSION, GaggiaState.BREWING, GaggiaState.DONE_BREWING)) {
                newAccumulatedTelemetry.addAll(existingValues)
            } else {
                if (existingValues.isNotEmpty()) {
                    existingValues[existingValues.size - 1].let {
                        newAccumulatedTelemetry.add(it)
                    }
                }
            }
            newAccumulatedTelemetry.add(newTelemetry)

            telemetryFlow.emit(
                telemetryFlow.value.copy(
                    telemetry = newAccumulatedTelemetry,
                    previousIsScaleWeighted = telemetryFlow.value.isScaleWeighted ?: false,
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
        viewModelScope.launch(Dispatchers.Default) {
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

    val weight: Weight,

    val pressureBars: String,
    val dutyCyclePercent: String,
    val flowRateGPS: String,

    val brewTempC: Temp,
    val shotsUntilBackflush: String,
    val totalShots: String,
    val boilerState: String) {

    fun toTelemetryMessageProto(): robo.ndipatri.robogaggia.proto_datastore_kmm.TelemetryMessage {
        return robo.ndipatri.robogaggia.proto_datastore_kmm.TelemetryMessage(
            state = state.toGaggiaStateProto(),
            weight = weight.toWeightProto(),
            pressureBars = pressureBars,
            dutyCyclePercent = dutyCyclePercent,
            flowRateGPS = flowRateGPS,
            brewTempC = brewTempC.toTempProto(),
            shotsUntilBackflush = shotsUntilBackflush,
            totalShots = totalShots,
            boilerState = boilerState
        )
    }
}


data class Weight(val currentWeight: Float, val targetWeight: Float?) {
    constructor(weight: String) : this(
        weight.trim().split(":")[0].toFloat(),
        if (weight.trim().split(":").size == 2) weight.trim().split(":")[1].toFloat() else null
    )

    fun toWeightProto(): robo.ndipatri.robogaggia.proto_datastore_kmm.Weight {
        return robo.ndipatri.robogaggia.proto_datastore_kmm.Weight(currentWeight = currentWeight, targetWeight = targetWeight ?: 0F)
    }
}

data class Temp(val currentTemp: Float, val targetTemp: Float?) {
    constructor(temp: String) : this(
        temp.trim().split(":")[0].toFloat(),
        if (temp.trim().split(":").size == 2) temp.trim().split(":")[1].toFloat() else null
    )

    fun toTempProto(): robo.ndipatri.robogaggia.proto_datastore_kmm.Temp {
        return robo.ndipatri.robogaggia.proto_datastore_kmm.Temp(currentTemp = currentTemp, targetTemp = targetTemp ?: 0F)
    }
}



enum class GaggiaState(val stateName: String) {
    IGNORING_NETWORK("ignoringNetwork"),
    JOINING_NETWORK("joiningNetwork"),
    SLEEP("sleep"),
    PREHEAT("preheat"),
    MEASURE_BEANS("measureBeans"),
    TARE_CUP_AFTER_MEASURE("tareCupAfterMeasure"),
    HEATING_TO_BREW("heating"),

    PREINFUSION("preInfusion"),
    BREWING("brewing"),

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

    fun toGaggiaStateProto(): robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState {
        return when (this) {
            IGNORING_NETWORK -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.IGNORING_NETWORK
            JOINING_NETWORK -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.JOINING_NETWORK
            SLEEP -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.SLEEP
            PREHEAT -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.PREHEAT
            MEASURE_BEANS -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.MEASURE_BEANS
            TARE_CUP_AFTER_MEASURE -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.TARE_CUP_AFTER_MEASURE
            HEATING_TO_BREW -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.HEATING_TO_BREW
            PREINFUSION -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.PREINFUSION
            BREWING -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BREWING
            DONE_BREWING -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.DONE_BREWING
            HEATING_TO_STEAM -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.HEATING_TO_STEAM
            STEAMING -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.STEAMING
            CLEAN_GROUP_READY -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.CLEAN_GROUP_READY
            CLEAN_GROUP_DONE -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.CLEAN_GROUP_DONE
            CLEAN_OPTIONS -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.CLEAN_OPTIONS
            DESCALE -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.DESCALE
            COOL_START -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.COOL_START
            COOLING -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.COOLING
            COOL_DONE -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.COOL_DONE
            BACKFLUSH_INSTRUCTION_1 -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BACKFLUSH_INSTRUCTION_1
            BACKFLUSH_INSTRUCTION_2 -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BACKFLUSH_INSTRUCTION_2
            BACKFLUSH_CYCLE_1 -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BACKFLUSH_CYCLE_1
            BACKFLUSH_INSTRUCTION_3 -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BACKFLUSH_INSTRUCTION_3
            BACKFLUSH_CYCLE_2 -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BACKFLUSH_CYCLE_2
            BACKFLUSH_CYCLE_DONE -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BACKFLUSH_CYCLE_DONE
            HEATING_TO_DISPENSE -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.HEATING_TO_DISPENSE
            DISPENSE_HOT_WATER -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.DISPENSE_HOT_WATER
            NA -> robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.NA
        }
    }

    companion object {
        fun byName(name: String): GaggiaState {
            for (candidate in GaggiaState.values()) {
                if (candidate.stateName == name) {
                    return candidate
                }
            }

            return NA
        }

        fun fromGaggiaProto(protoGaggiaState: robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState): GaggiaState {
            return when (protoGaggiaState) {
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.IGNORING_NETWORK -> IGNORING_NETWORK
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.JOINING_NETWORK -> JOINING_NETWORK
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.SLEEP -> SLEEP
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.PREHEAT -> PREHEAT
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.MEASURE_BEANS -> MEASURE_BEANS
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.TARE_CUP_AFTER_MEASURE -> TARE_CUP_AFTER_MEASURE
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.HEATING_TO_BREW -> HEATING_TO_BREW
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.PREINFUSION -> PREINFUSION
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BREWING -> BREWING
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.DONE_BREWING -> DONE_BREWING
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.HEATING_TO_STEAM -> HEATING_TO_STEAM
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.STEAMING -> STEAMING
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.CLEAN_GROUP_READY -> CLEAN_GROUP_READY
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.CLEAN_GROUP_DONE -> CLEAN_GROUP_DONE
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.CLEAN_OPTIONS -> CLEAN_OPTIONS
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.DESCALE -> DESCALE
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.COOL_START -> COOL_START
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.COOLING -> COOLING
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.COOL_DONE -> COOL_DONE
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BACKFLUSH_INSTRUCTION_1 -> BACKFLUSH_INSTRUCTION_1
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BACKFLUSH_INSTRUCTION_2 -> BACKFLUSH_INSTRUCTION_2
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BACKFLUSH_CYCLE_1 -> BACKFLUSH_CYCLE_1
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BACKFLUSH_INSTRUCTION_3 -> BACKFLUSH_INSTRUCTION_3
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BACKFLUSH_CYCLE_2 -> BACKFLUSH_CYCLE_2
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.BACKFLUSH_CYCLE_DONE -> BACKFLUSH_CYCLE_DONE
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.HEATING_TO_DISPENSE -> HEATING_TO_DISPENSE
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.DISPENSE_HOT_WATER -> DISPENSE_HOT_WATER
                robo.ndipatri.robogaggia.proto_datastore_kmm.GaggiaState.NA -> NA
            }
        }
    }
}


// These are upper and lower values for a classic Schmitt Trigger to prevent bouncing
// when determining if scale is weighted ...

// The treshold nees to be a big higher/lower than the real value
// to allow for the trigger
val WEIGHT_OF_EMPTY_SCALE_GRAMS = 11F
val SCHMITT_TRIGGER_THRESHOLD = 1.1F
fun lowerWeightedThresholdGrams(state: GaggiaState): Float? {
    return when (state) {
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

fun upperWeightedThresholdGrams(state: GaggiaState): Float? {
    return when (state) {
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


data class Telemetry(
    val telemetry: List<TelemetryMessage> = listOf(),
    val previousIsScaleWeighted: Boolean = false,
) {

    constructor(telemetry: TelemetryProtoData) : this(
        telemetry = telemetry.telemetry.map {
            TelemetryMessage(
                state = GaggiaState.fromGaggiaProto(it.state),
                weight = Weight(currentWeight = it.weight?.currentWeight ?: 0F, targetWeight = it.weight?.targetWeight),
                pressureBars = it.pressureBars,
                dutyCyclePercent = it.dutyCyclePercent,
                flowRateGPS = it.flowRateGPS,
                brewTempC = Temp(currentTemp = it.brewTempC?.currentTemp ?: 0F, targetTemp = it.brewTempC?.targetTemp),
                shotsUntilBackflush = it.shotsUntilBackflush,
                totalShots = it.totalShots,
                boilerState = it.boilerState
            )
        }
    )

    // 'weighted' means that the scale has something on it that we care about
    // This shows raw weight without waiting for scale to settle.
    val isScaleWeightedRaw: Boolean
        get() {
            val state = currentTelemetryMessage?.state ?: return false
            val measuredWeightGrams = currentTelemetryMessage?.weight?.currentWeight ?: return false

            return (previousIsScaleWeighted &&
                    lowerWeightedThresholdGrams(state) != null &&
                    measuredWeightGrams >= lowerWeightedThresholdGrams(state)!!) ||

                    (!previousIsScaleWeighted &&
                            upperWeightedThresholdGrams(state) != null &&
                            measuredWeightGrams > upperWeightedThresholdGrams(state)!!)
        }

    // 'settled' means the scale has finally stopped changing in value enough to consider the value for calculations
    // For settling, only consider decimal value of weight
    val SETTLED_WEIGHT_THRESHOLD = 10
    val isScaleSettled =
        if (currentTelemetryMessage?.weight?.currentWeight != null && previousTelemetryMessage?.weight?.currentWeight != null) {
            abs(
                (currentTelemetryMessage!!.weight.currentWeight.toInt() -
                        (previousTelemetryMessage!!.weight.currentWeight.toInt()))
            ) < SETTLED_WEIGHT_THRESHOLD
        } else {
            false
        }

    // if something is on the scale
    val isScaleWeighted: Boolean
        get() {
            return isScaleSettled && isScaleWeightedRaw
        }

    // 'tared' in this case means this weight represents not necessarily
    // everything on the scale, but what is supposed to be measured.. For
    // example, if the scale has a cup filled with beans, the value here is
    // just the weight of the beans.
    val currentTaredWeight: Float?
        get() {
            return if (telemetry.isNotEmpty()) {
                telemetry.last().weight.currentWeight
            } else {
                null
            }
        }

    val currentTemperature: Float?
        get() {
            return if (telemetry.isNotEmpty()) {
                telemetry.last().brewTempC.currentTemp
            } else {
                null
            }
        }

    val targetTemperature: Float?
        get() {
            return if (telemetry.isNotEmpty()) {
                telemetry.last().brewTempC.targetTemp
            } else {
                null
            }
        }

    val currentState: GaggiaState?
        get() {
            return if (telemetry.isNotEmpty()) {
                telemetry.last().state
            } else {
                null
            }
        }

    val currentShotsUntilBackflush: Int?
        get() {
            return if (telemetry.isNotEmpty()) {
                telemetry.last().shotsUntilBackflush.trim().toInt()
            } else {
                null
            }
        }

    val currentBoilerIsOn: Boolean?
        get() {
            return if (telemetry.isNotEmpty()) {
                if (telemetry.last().boilerState.trim().toInt() == 1) {
                    true
                } else {
                    false
                }
            } else {
                null
            }
        }

    val currentTotalShots: Int?
        get() {
            return if (telemetry.isNotEmpty()) {
                telemetry.last().totalShots.trim().toInt()
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

    // Only valid during TARE_CUP_AFTER_MEASURE state
    // This is because at this time, the telemetry 'weightGrams' value
    // is tared with the cup, so if the weight is close to zero, this means
    // the cup is back on the scale.
    val isCupOnlyOnScale: Boolean =
        if (currentTelemetryMessage?.state == GaggiaState.TARE_CUP_AFTER_MEASURE) {
            isScaleSettled && (currentTelemetryMessage?.weight?.currentWeight
                ?.let { abs(it) < 3F } ?: false)
        } else {
            false
        }
}
