package vms

import MQTTClient
import com.ndipatri.robogaggia.BuildKonfig
import currentTimeMillis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mqtt.MQTTVersion
import mqtt.Subscription
import mqtt.packets.Qos
import mqtt.packets.mqttv5.SubscriptionOptions
import utils.renderTelemetry
import utils.typicalBrewCycleTelemetryString
import vms.TelemetryViewModel.Companion.telemetryTopic

// This implements a simple state machine in an attempt to
// simulate the behavior of RoboGaggia (RG).. for testing this mobile
// app
class GaggiaSimulator(val coroutineScope: CoroutineScope) {

    lateinit var client: MQTTClient

    var currentTelemetry = TelemetryMessage(GaggiaState.NA, "0.0", "0.0", "0.0", "0.0", "0.0")

    var timeSinceLastCommandMillis = currentTimeMillis()

    // How long until Gaggia redirects to the SLEEP state
    var USER_INACTIVITY_TIMEOUT_MILLIS = 300000L

    init {
        startClientAndSubscribeToCommandTopic(600)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    fun startClientAndSubscribeToCommandTopic(startDelayMillis: Long) {

        coroutineScope.launch(Dispatchers.Default) {
            var connected = false
            while (true) {
                delay(startDelayMillis)

                client = MQTTClient(
                    mqttVersion = MQTTVersion.MQTT3_1_1,
                    clientId = currentTimeMillis().toString(),
                    address = BuildKonfig.MQTT_SERVER_ADDRESS,
                    port = 1883,
                    tls = null,
                    userName = BuildKonfig.AIO_USERNAME,
                    password = BuildKonfig.AIO_PASSWORD.encodeToByteArray()
                        .toUByteArray(),
                ) { mqttPublish ->
                    mqttPublish.payload?.let {
                        val message = it.toByteArray().decodeToString()
                        handleIncomingCommand(message)
                    }
                }

                try {
                    client.subscribe(subscriptionTopic)

                    sendTelemetryContinuously()

                    println("*** Simulator: connecting to MQTT broker.")
                    // will throw exception if not connected...
                    while (client.running) {

                        // This has a delay to make this loop kind to the CPU
                        client.step()
                        delay(250)

                        if (!connected) {
                            // we need to make sure we're connected first before we
                            // try to publish.. duh

                            currentTelemetry = currentTelemetry.copy(state = GaggiaState.JOINING_NETWORK)
                            //currentTelemetry = currentTelemetry.copy(state = GaggiaState.PREINFUSION_AND_BREWING)

                            scheduleNextStateAutomaticallyIfNecessary()
                        }
                        connected = true

                        checkForUserInactivityTimeout()
                    }
                } catch (ex: Exception) {
                    println("*** Simulator: failed to connect to MQTT broker: $ex")
                }
            }
        }
    }

    private fun checkForUserInactivityTimeout() {
        val now = currentTimeMillis()

        if (now - timeSinceLastCommandMillis > USER_INACTIVITY_TIMEOUT_MILLIS) {
            if (currentTelemetry.state != GaggiaState.SLEEP) {
                println("*** Simulator: user inactivity. moving to sleep state.")

                currentTelemetry = currentTelemetry.copy(state = GaggiaState.SLEEP)
            }
        }
    }

    private fun handleIncomingCommand(command: String) {
        println("*** Simulator: Incoming command: $command")
        timeSinceLastCommandMillis = currentTimeMillis()

        currentTelemetry = currentTelemetry.copy(
            state = getNextState(
                currentTelemetry.state,
                CommandType.byTransmitName(command)
            )
        )

        scheduleNextStateAutomaticallyIfNecessary()
    }

    // If we receive a command from mobile, we need to move to a new state....
    private fun getNextState(currentState: GaggiaState, commandType: CommandType): GaggiaState {

        when (currentState) {
            GaggiaState.SLEEP -> {
                return when (commandType) {
                    CommandType.FIRST_BUTTON_CLICK, CommandType.SECOND_BUTTON_CLICK -> {
                        GaggiaState.PREHEAT
                    }
                }
            }

            GaggiaState.JOINING_NETWORK -> {
                return when (commandType) {
                    CommandType.FIRST_BUTTON_CLICK -> {
                        // User has aborted joining network...
                        GaggiaState.IGNORING_NETWORK
                    }

                    else -> currentState
                }
            }

            GaggiaState.PREHEAT -> {
                return when (commandType) {
                    CommandType.FIRST_BUTTON_CLICK -> {
                        GaggiaState.MEASURE_BEANS
                    }

                    CommandType.SECOND_BUTTON_CLICK -> {
                        // user has aborted
                        GaggiaState.CLEAN_OPTIONS
                    }
                }
            }

            GaggiaState.CLEAN_OPTIONS -> {
                return when (commandType) {
                    CommandType.FIRST_BUTTON_CLICK -> {
                        GaggiaState.BACKFLUSH_INSTRUCTION_1
                    }

                    CommandType.SECOND_BUTTON_CLICK -> {
                        // user has aborted
                        GaggiaState.DESCALE
                    }
                }
            }

            GaggiaState.DESCALE -> {
                return when (commandType) {
                    CommandType.FIRST_BUTTON_CLICK -> {
                        // internally the gaggia has measured the weight of the beans
                        GaggiaState.HEATING_TO_DISPENSE
                    }

                    CommandType.SECOND_BUTTON_CLICK -> {
                        // user has aborted
                        GaggiaState.PREHEAT
                    }
                }
            }

            GaggiaState.HEATING_TO_DISPENSE -> {
                return when (commandType) {
                    CommandType.SECOND_BUTTON_CLICK -> {
                        // user has aborted
                        GaggiaState.PREHEAT
                    }

                    else -> currentState
                }
            }

            GaggiaState.DISPENSE_HOT_WATER -> {
                return when (commandType) {
                    CommandType.FIRST_BUTTON_CLICK -> {
                        // user has aborted
                        GaggiaState.PREHEAT
                    }

                    else -> currentState
                }
            }

            GaggiaState.MEASURE_BEANS -> {
                return when (commandType) {
                    CommandType.FIRST_BUTTON_CLICK -> {
                        // internally the gaggia has measured the weight of the beans
                        GaggiaState.TARE_CUP_AFTER_MEASURE
                    }

                    CommandType.SECOND_BUTTON_CLICK -> {
                        // user has aborted
                        GaggiaState.PREHEAT
                    }
                }
            }

            GaggiaState.TARE_CUP_AFTER_MEASURE -> {
                return when (commandType) {
                    CommandType.FIRST_BUTTON_CLICK -> {
                        GaggiaState.HEATING_TO_BREW
                    }

                    CommandType.SECOND_BUTTON_CLICK -> {
                        // user has aborted
                        GaggiaState.PREHEAT
                    }
                }
            }

            GaggiaState.HEATING_TO_BREW -> {
                return when (commandType) {
                    CommandType.SECOND_BUTTON_CLICK -> {
                        // user has aborted
                        GaggiaState.PREHEAT
                    }

                    else -> {
                        currentState
                    }
                }
            }

            GaggiaState.DONE_BREWING -> {
                return when (commandType) {
                    CommandType.FIRST_BUTTON_CLICK -> {
                        GaggiaState.HEATING_TO_STEAM
                    }

                    CommandType.SECOND_BUTTON_CLICK -> {
                        // user has aborted
                        GaggiaState.PREHEAT
                    }
                }
            }

            GaggiaState.STEAMING -> {
                return when (commandType) {
                    CommandType.FIRST_BUTTON_CLICK, CommandType.SECOND_BUTTON_CLICK -> {
                        GaggiaState.CLEAN_GROUP_READY
                    }
                }
            }

            GaggiaState.CLEAN_GROUP_READY -> {
                return when (commandType) {
                    CommandType.FIRST_BUTTON_CLICK, CommandType.SECOND_BUTTON_CLICK -> {
                        GaggiaState.CLEAN_GROUP_DONE
                    }
                }
            }

            GaggiaState.CLEAN_GROUP_DONE -> {
                return when (commandType) {
                    CommandType.FIRST_BUTTON_CLICK, CommandType.SECOND_BUTTON_CLICK -> {
                        GaggiaState.PREHEAT
                    }
                }
            }

            else -> {}
        }

        return GaggiaState.NA
    }

    private fun scheduleNextStateAutomaticallyIfNecessary() {

        // Sometimes moving to a state triggers internal behavior
        // which may result in a state change.... this is where we
        // are truly simulating internal gaggia behavior
        //
        // We also simulate user behavior in here as well.. e.g. putting a
        // cup or beans on the scale...

        when (currentTelemetry.state) {
            GaggiaState.JOINING_NETWORK -> {
                coroutineScope.launch(Dispatchers.Default) {
                    // assume it takes 5 seconds to join the network
                    delay(2000)

                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.PREHEAT)

                    scheduleNextStateAutomaticallyIfNecessary()
                }
            }

            GaggiaState.IGNORING_NETWORK -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // assume gaggia has now stopped trying to join network...
                    delay(3000)

                    currentTelemetry= currentTelemetry.copy(state = GaggiaState.PREHEAT)

                    scheduleNextStateAutomaticallyIfNecessary()
                }
            }

            GaggiaState.PREHEAT -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // we simulate the scale first being empty and then putting
                    // their empty cup on the scale ...

                    delay(3000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.PREHEAT, weightGrams = "2")

                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.PREHEAT, weightGrams = "4.5")

                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.PREHEAT, weightGrams = "23.1")

                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.PREHEAT, weightGrams = "30.5")

                    // weight of scale + cup
                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.PREHEAT, weightGrams = "50.5")

                    // This should trigger a 'scale settled' event in the client.
                    // and the weight of the scale and cup should register
                    // on the app.
                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.PREHEAT, weightGrams = "50.5")

                    // we don't trigger any more state changes as now we wait for user to interact.
                }
            }

            GaggiaState.MEASURE_BEANS -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // simulate somebody slowly filling the cup with beans
                    //
                    // at first.. only thing on scale is the cup... but remember,
                    // this is tared weight so we start at 0
                    delay(4000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.MEASURE_BEANS, weightGrams = "0.0")

                    // at first.. only thing on scale is the cup...
                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.MEASURE_BEANS, weightGrams = "2.5")

                    // finally, they begin to pour beans in the cup
                    // the scale usually jumps up when an object is first placed...
                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.MEASURE_BEANS, weightGrams = "15.0")

                    // let's change hte value a bit so it has to settle..
                    delay (1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.MEASURE_BEANS, weightGrams = "18.0")

                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.MEASURE_BEANS, weightGrams = "25.0")

                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.MEASURE_BEANS, weightGrams = "20.0")

                    delay(1000)
                    // assume 19 grams of beans
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.MEASURE_BEANS, weightGrams = "19.0")

                    // this should finally trigger the app to declare bean value settled...
                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.MEASURE_BEANS, weightGrams = "19.0")

                    // we don't trigger any more state changes as now we wait for user to interact.
                }
            }

            GaggiaState.TARE_CUP_AFTER_MEASURE -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // we're simulating the user taking the cup full of beans off
                    // the scale, grinding the beans, then replacing the empty cup
                    // on the scale...
                    //
                    // The thing to note here is that at this point in the process, the gaggia
                    // has tared the scale and the empty cup.. so any measured weight is only
                    // the beans... so an empty reading means the cup is on the scale...

                    delay(1000)
                    // removing the cup makes the weight go negative because it's tared with
                    // the weight of hte cup...
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.TARE_CUP_AFTER_MEASURE, weightGrams = "-1.0")

                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.TARE_CUP_AFTER_MEASURE, weightGrams = "-2.0")

                    delay(1000)
                    // while the scale is settling, the user is emptying the cup and putting the
                    // beans in the portrafilter.. and returning the empty cup to the scale...
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.TARE_CUP_AFTER_MEASURE, weightGrams = "-2.0")

                    // finally the user paces the cup ...
                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.TARE_CUP_AFTER_MEASURE, weightGrams = "-1.0")

                    delay(1000)
                    // Assume the cup that has been placed on scale weighs
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.TARE_CUP_AFTER_MEASURE, weightGrams = "0.0")

                    // Finally trigger the cup settled on the scale.
                    delay(1000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.TARE_CUP_AFTER_MEASURE, weightGrams = "0.0")

                    // we don't trigger any more state changes as now we wait for user to interact.
                }
            }

            GaggiaState.HEATING_TO_BREW -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // assume it takes 5 seconds to preheat
                    delay(2000)
                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.PREINFUSION_AND_BREWING)

                    scheduleNextStateAutomaticallyIfNecessary()
                }
            }

            GaggiaState.PREINFUSION_AND_BREWING -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // it takes some time for preinfusion and brewing
                    for (telemetryMessage in renderTelemetry(typicalBrewCycleTelemetryString)) {
                        currentTelemetry = currentTelemetry.copy(
                            state = telemetryMessage.state,
                            weightGrams = telemetryMessage.weightGrams,
                            pressureBars = telemetryMessage.pressureBars,
                            dutyCyclePercent = telemetryMessage.dutyCyclePercent,
                            flowRateGPS = telemetryMessage.flowRateGPS,
                            brewTempC = telemetryMessage.brewTempC
                        )

                        //delay(BuildKonfig.MQTT_SERVER_ADDRESS)
                        delay(BuildKonfig.MQTT_SERVER_TELEMETRY_INTERVAL_MILLIS.toLong())
                    }

                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.DONE_BREWING)
                    // we don't trigger any more state changes as now we wait for user to interact.
                }
            }

            GaggiaState.HEATING_TO_STEAM -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // it takes some time to brew
                    delay(4000)

                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.STEAMING)
                    // we don't trigger any more state changes as now we wait for user to interact.
                }
            }

            GaggiaState.CLEAN_GROUP_DONE -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // assume it takes a couple seconds to clean
                    delay(2000)

                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.PREHEAT)

                    scheduleNextStateAutomaticallyIfNecessary()
                }
            }

            GaggiaState.HEATING_TO_DISPENSE -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // assume it takes 5 seconds to preheat
                    delay(5000)

                    currentTelemetry = currentTelemetry.copy(state = GaggiaState.DISPENSE_HOT_WATER)
                }
            }

            else -> {}
        }
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    private fun sendTelemetryContinuously() {
        coroutineScope.launch(Dispatchers.Default) {

            // We continuously transmit telemetry to the app
            while (true) {
                val payload =
                    currentTelemetry.state.stateName + ", " + currentTelemetry.weightGrams + ", " + currentTelemetry.pressureBars + ", " + currentTelemetry.dutyCyclePercent + ", " + currentTelemetry.flowRateGPS + ", " + currentTelemetry.brewTempC

                println("*** Simulator: publishing message: $payload")

                client.publish(
                    retain = false,
                    qos = Qos.AT_MOST_ONCE,
                    topic = telemetryTopic,
                    payload = payload.encodeToByteArray().toUByteArray()
                )
                client.step()

                delay(BuildKonfig.MQTT_SERVER_TELEMETRY_INTERVAL_MILLIS.toLong())
            }
        }
    }

    companion object {
        private val subscriptionTopic = listOf(
            Subscription(
                TelemetryViewModel.commandTopic,
                SubscriptionOptions(Qos.AT_MOST_ONCE)
            )
        )
    }
}


