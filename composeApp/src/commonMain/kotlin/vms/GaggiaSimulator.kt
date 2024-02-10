package vms

import MQTTClient
import com.ndipatri.robogaggia.BuildKonfig
import currentTimeMillis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
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

    lateinit var currentState: GaggiaState

    var timeSinceLastCommandMillis = currentTimeMillis()

    // How long until Gaggia redirects to the SLEEP state
    var USER_INACTIVITY_TIMEOUT_MILLIS = 300000L

    // We use these just to help us know when to stop sending fake telemetry
    var scaleSettled = false
    var scaleWeighted = false

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
                    mqttVersion = MQTTVersion.MQTT5,
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

                    println("*** Simulator connecting to MQTT broker.")
                    // will throw exception if not connected...
                    while (client.running) {

                        // This has a delay to make this loop kind to the CPU
                        client.step()

                        if (!connected) {
                            // we need to make sure we're connected first before we
                            // try to publish.. duh
                            moveToState(GaggiaState.JOINING_NETWORK)
                        }
                        connected = true

                        checkForUserInactivityTimeout()
                    }
                } catch (ex: Exception) {
                    println("*** Simulator failed to connect to MQTT broker: $ex")
                }
            }
        }
    }

    private fun checkForUserInactivityTimeout() {
        val now = currentTimeMillis()

        if (now - timeSinceLastCommandMillis > USER_INACTIVITY_TIMEOUT_MILLIS) {
            if (currentState != GaggiaState.SLEEP) {
                println("Simulator: user inactivity. moving to sleep state.")
                moveToState(GaggiaState.SLEEP)
            }
        }
    }

    private fun handleIncomingCommand(command: String) {
        println("Simulator: Incoming command: $command")

        moveToState(getNextState(currentState, CommandType.byTransmitName(command)))
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

                    else -> { currentState }
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

    private fun scheduleNextStateAutomaticallyIfNecessary(currentState: GaggiaState) {
        when (currentState) {
            GaggiaState.JOINING_NETWORK -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // assume it takes 5 seconds to join the network
                    delay(5000)

                    moveToState(GaggiaState.PREHEAT)
                }
            }

            GaggiaState.IGNORING_NETWORK -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // assume gaggia has now stopped trying to join network...
                    delay(3000)

                    moveToState(GaggiaState.PREHEAT)
                }
            }

            GaggiaState.PREHEAT -> {
                if (!scaleSettled) {
                    coroutineScope.launch(Dispatchers.Default) {
                        scaleSettled = true

                        // we simulate the scale first being empty and then putting
                        // their empty cup on the scale ...

                        delay(3000)
                        moveToState(GaggiaState.PREHEAT, measuredWeightGrams = "2")

                        delay(1000)
                        moveToState(GaggiaState.PREHEAT, measuredWeightGrams = "4.5")

                        delay(1000)
                        moveToState(GaggiaState.PREHEAT, measuredWeightGrams = "23.1")

                        delay(1000)
                        moveToState(GaggiaState.PREHEAT, measuredWeightGrams = "30.5")

                        // weight of scale + cup
                        delay(1000)
                        moveToState(GaggiaState.PREHEAT, measuredWeightGrams = "50.5")

                        // This should trigger a 'scale settled' event in the client.
                        // and the weight of the scale and cup should register
                        // on the app.
                        delay(1000)
                        moveToState(GaggiaState.PREHEAT, measuredWeightGrams = "50.5")

                        scaleSettled = false
                    }
                }
            }

            GaggiaState.MEASURE_BEANS -> {
                if (!scaleWeighted) {
                    coroutineScope.launch(Dispatchers.Default) {
                        scaleWeighted = true

                        // simulate somebody slowly filling the cup with beans
                        //
                        // at first.. only thing on scale is the cup... but remember,
                        // this is tared weight so we start at 0
                        delay(4000)
                        moveToState(GaggiaState.MEASURE_BEANS, measuredWeightGrams = "0.0")

                        // at first.. only thing on scale is the cup...
                        delay(1000)
                        moveToState(GaggiaState.MEASURE_BEANS, measuredWeightGrams = "2.5")

                        // finally, they begin to pour beans in the cup
                        // the scale usually jumps up when an object is first placed...
                        delay(1000)
                        moveToState(GaggiaState.MEASURE_BEANS, measuredWeightGrams = "15.0")

                        // let's change hte value a bit so it has to settle..
                        delay(1000)
                        moveToState(GaggiaState.MEASURE_BEANS, measuredWeightGrams = "18.0")

                        delay(1000)
                        moveToState(GaggiaState.MEASURE_BEANS, measuredWeightGrams = "25.0")

                        delay(1000)
                        moveToState(GaggiaState.MEASURE_BEANS, measuredWeightGrams = "20.0")

                        delay(1000)
                        // assume 19 grams of beans
                        moveToState(GaggiaState.MEASURE_BEANS, measuredWeightGrams = "19.0")

                        // this should finally trigger the app to declare bean value settled...
                        delay(1000)
                        moveToState(GaggiaState.MEASURE_BEANS, measuredWeightGrams = "19.0")

                        scaleWeighted = false
                    }
                }
            }

            GaggiaState.TARE_CUP_AFTER_MEASURE -> {
                if (!scaleWeighted) {
                    coroutineScope.launch(Dispatchers.Default) {
                        scaleWeighted = true

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
                        moveToState(GaggiaState.TARE_CUP_AFTER_MEASURE, measuredWeightGrams = "-1.0")

                        delay(1000)
                        moveToState(GaggiaState.TARE_CUP_AFTER_MEASURE, measuredWeightGrams = "-2.0")

                        delay(1000)
                        // while the scale is settling, the user is emptying the cup and putting the
                        // beans in the portrafilter.. and returning the empty cup to the scale...
                        moveToState(GaggiaState.TARE_CUP_AFTER_MEASURE, measuredWeightGrams = "-2.0")

                        // finally the user paces the cup ...
                        delay(1000)
                        moveToState(GaggiaState.TARE_CUP_AFTER_MEASURE, measuredWeightGrams = "-1.0")

                        delay(1000)
                        // Assume the cup that has been placed on scale weighs
                        moveToState(GaggiaState.TARE_CUP_AFTER_MEASURE, measuredWeightGrams = "0.0")

                        // Finally trigger the cup settled on the scale.
                        delay(1000)
                        moveToState(GaggiaState.TARE_CUP_AFTER_MEASURE, measuredWeightGrams = "0.0")

                        scaleWeighted = false
                    }
                }
            }

            GaggiaState.HEATING_TO_BREW -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // assume it takes 5 seconds to preheat
                    delay(2000)

                    moveToState(GaggiaState.PREINFUSION_AND_BREWING)
                }
            }

            GaggiaState.PREINFUSION_AND_BREWING -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // it takes some time for preinfusion and brewing
                    for (telemetryMessage in renderTelemetry(typicalBrewCycleTelemetryString)) {
                        publishTelemetryMessage("${telemetryMessage.state}, ${telemetryMessage.weightGrams}, ${telemetryMessage.pressureBars}, ${telemetryMessage.dutyCyclePercent}, ${telemetryMessage.flowRateGPS}, ${telemetryMessage.brewTempC}")
                        delay(1000)
                    }

                    moveToState(GaggiaState.DONE_BREWING)
                }
            }

            GaggiaState.HEATING_TO_STEAM -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // it takes some time to brew
                    delay(4000)

                    moveToState(GaggiaState.STEAMING)
                }
            }

            GaggiaState.CLEAN_GROUP_DONE -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // assume it takes a couple seconds to clean
                    delay(2000)

                    moveToState(GaggiaState.PREHEAT)
                }
            }

            GaggiaState.HEATING_TO_DISPENSE -> {
                coroutineScope.launch(Dispatchers.Default) {

                    // assume it takes 5 seconds to preheat
                    delay(5000)

                    moveToState(GaggiaState.DISPENSE_HOT_WATER)
                }
            }

            else -> {}
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun moveToState(nextState: GaggiaState, measuredWeightGrams: String = "0.058937") {
        timeSinceLastCommandMillis = currentTimeMillis()
        currentState = nextState
        coroutineScope.launch(Dispatchers.Default) {
            // Only the state matters here so far; we don't care about other values.
            publishTelemetryMessage("${currentState.stateName}, $measuredWeightGrams, 0.000000, 0, 0.000000, 0.000000")
        }

        // Sometimes moving to a state triggers internal behavior
        // which may result in a state change.... this is where we
        // are truly simulating internal gaggia behavior
        //
        // We also simulate user behavior in here as well.. e.g. putting a
        // cup or beans on the scale...
        scheduleNextStateAutomaticallyIfNecessary(currentState)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    private fun publishTelemetryMessage(payload: String) {
        println("*** publishing message: $payload")
        client.publish(
            retain = false,
            qos = Qos.AT_MOST_ONCE,
            topic = telemetryTopic,
            payload = payload.encodeToByteArray().toUByteArray()
        )
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


