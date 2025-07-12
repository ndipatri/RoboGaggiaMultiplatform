package services

import com.xemantic.ai.anthropic.Anthropic
import com.xemantic.ai.anthropic.content.ToolUse
import com.xemantic.ai.anthropic.message.Message
import com.xemantic.ai.anthropic.message.StopReason
import com.xemantic.ai.anthropic.tool.Tool
import com.xemantic.ai.tool.schema.meta.Description
import currentTimeMillis
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class MCPManager(val apiKey: String, val coroutineScope: CoroutineScope) {

    suspend fun executeQuery(query: String): String {
        return try {
            // Create Anthropic client
            val anthropic = Anthropic {
                apiKey = this@MCPManager.apiKey
            }

            println("*** NJD: Executing query against Claude: $query using API Key: $apiKey")

            val conversation = mutableListOf(Message {
                +query
            })

            var done = false
            while(!done) {
                // Try a direct API call approach
                val response = anthropic.messages.create {
                    model = "claude-3-5-sonnet-20241022"
                    maxTokens = 1000
                    +Message {
                        messages = conversation
                        tools = availableTools
                    }
                }
                // we need to keep a running conversation
                conversation += response.asMessage()
                println("*** NJD: primary answer: $conversation")

                if (response.stopReason == StopReason.END_TURN) {
                    done = true
                    continue
                }

                response.content.firstOrNull { it is ToolUse }?.let { content ->

                    // If the response indicates a tool use, process it further
                    conversation += response.useTools()

                    // Send answer back to LLM so it can synthesize it with natural
                    // language answer...
                    val response = anthropic.messages.create {
                        model = "claude-3-5-sonnet-20241022"
                        maxTokens = 1000
                        +Message {
                            messages = conversation
                            tools = availableTools
                        }
                    }
                    conversation += response.asMessage()

                    if (response.stopReason == StopReason.END_TURN) {
                        done = true
                    }
                }

                println("*** NJD: Received response from Claude: $conversation")
            }

            conversation.toString()

        } catch (e: Exception) {
            println("*** NJD: Error executing query: ${e.message}")
            e.printStackTrace()
            "Error executing query: ${e.message}"
        }
    }

    @Serializable
    @SerialName("get_first_name")
    @Description("Get first name for the user with the given last name")
    data class GetFirstNameTool(val lastName: String)

    val availableTools = listOf(
        Tool<GetFirstNameTool> {
            when (lastName) {
                "Smith" -> "John"
                "Johnson" -> "Emily"
                "Williams" -> "Michael"
                "Brown" -> "Sarah"
                "Jones" -> "David"
                else -> "Unknown"
            }
        }
    )
}

data class MCPQuery(
    val queryString: String,
    val timestamp: Long = currentTimeMillis()
)

const val STATE_MERMAID_DOCUMENT = """"
stateDiagram-v2
    [*] --> JOINING_NETWORK
    note right of JOINING_NETWORK
      transient
    end note
    JOINING_NETWORK --> PREHEAT : networkState.connected
    JOINING_NETWORK --> IGNORING_NETWORK : SHORT_PRESS/LONG_PRESS

    IGNORING_NETWORK --> PREHEAT : WiFi.off
    note right of IGNORING_NETWORK : transient

    PREHEAT --> MEASURE_BEANS : SHORT_PRESS & temp < 1.5×TARGET_BREW_TEMP
    PREHEAT --> COOL_START : SHORT_PRESS & temp ≥ 1.5×TARGET_BREW_TEMP
    PREHEAT --> CLEAN_OPTIONS : LONG_PRESS
    note right of PREHEAT : steady

    MEASURE_BEANS --> TARE_CUP_AFTER_MEASURE : SHORT_PRESS
    MEASURE_BEANS --> PREHEAT : LONG_PRESS
    note right of MEASURE_BEANS : steady

    TARE_CUP_AFTER_MEASURE --> HEATING_TO_BREW : SHORT_PRESS
    TARE_CUP_AFTER_MEASURE --> PREHEAT : LONG_PRESS
    note right of TARE_CUP_AFTER_MEASURE : steady

    HEATING_TO_BREW --> PREINFUSION : heater ≥ TARGET_BREW_TEMP
    HEATING_TO_BREW --> PREHEAT : LONG_PRESS
    note right of HEATING_TO_BREW : transient

    PREINFUSION --> BREWING : weight > threshold
    PREINFUSION --> PREHEAT : LONG_PRESS
    note right of PREINFUSION : transient

    BREWING --> DONE_BREWING : weight ≥ target
    BREWING --> PREHEAT : LONG_PRESS
    note right of BREWING : transient

    DONE_BREWING --> HEATING_TO_STEAM : SHORT_PRESS
    DONE_BREWING --> PREHEAT : LONG_PRESS
    note right of DONE_BREWING : steady

    HEATING_TO_STEAM --> STEAMING : heater ≥ TARGET_STEAM_TEMP
    HEATING_TO_STEAM --> PREHEAT : LONG_PRESS
    note right of HEATING_TO_STEAM : transient

    STEAMING --> GROUP_CLEAN_2 : SHORT_PRESS/LONG_PRESS
    note right of STEAMING : steady

    GROUP_CLEAN_2 --> GROUP_CLEAN_3 : SHORT_PRESS
    GROUP_CLEAN_2 --> PREHEAT : LONG_PRESS
    note right of GROUP_CLEAN_2 : steady

    GROUP_CLEAN_3 --> PREHEAT : timeout or LONG_PRESS
    note right of GROUP_CLEAN_3 : transient

    CLEAN_OPTIONS --> DESCALE : SHORT_PRESS
    CLEAN_OPTIONS --> BACKFLUSH_INSTRUCTION_1 : LONG_PRESS
    note right of CLEAN_OPTIONS : steady

    DESCALE --> HEATING_TO_DISPENSE : SHORT_PRESS
    DESCALE --> PREHEAT : LONG_PRESS
    note right of DESCALE : steady

    HEATING_TO_DISPENSE --> DISPENSE_HOT_WATER : heater ≥ TARGET_HOT_WATER_DISPENSE_TEMP
    HEATING_TO_DISPENSE --> PREHEAT : LONG_PRESS
    note right of HEATING_TO_DISPENSE : transient

    DISPENSE_HOT_WATER --> PREHEAT : SHORT_PRESS/LONG_PRESS
    note right of DISPENSE_HOT_WATER : steady

    COOL_START --> COOLING : SHORT_PRESS
    COOL_START --> PREHEAT : LONG_PRESS
    note right of COOL_START : steady

    COOLING --> COOL_DONE : SHORT_PRESS or temp < TARGET_BREW_TEMP
    COOLING --> PREHEAT : LONG_PRESS
    note right of COOLING : transient

    COOL_DONE --> COOL_START : SHORT_PRESS & temp ≥ TARGET_BREW_TEMP
    COOL_DONE --> PREHEAT : SHORT_PRESS (temp < target) or LONG_PRESS
    note right of COOL_DONE : steady

    BACKFLUSH_INSTRUCTION_1 --> BACKFLUSH_INSTRUCTION_2 : SHORT_PRESS
    BACKFLUSH_INSTRUCTION_1 --> PREHEAT : LONG_PRESS
    note right of BACKFLUSH_INSTRUCTION_1 : steady

    BACKFLUSH_INSTRUCTION_2 --> BACKFLUSH_CYCLE_1 : SHORT_PRESS
    BACKFLUSH_INSTRUCTION_2 --> PREHEAT : LONG_PRESS
    note right of BACKFLUSH_INSTRUCTION_2 : steady

    BACKFLUSH_CYCLE_1 --> BACKFLUSH_INSTRUCTION_3 : counter reached
    BACKFLUSH_CYCLE_1 --> PREHEAT : LONG_PRESS
    note right of BACKFLUSH_CYCLE_1 : transient

    BACKFLUSH_INSTRUCTION_3 --> BACKFLUSH_CYCLE_2 : SHORT_PRESS
    BACKFLUSH_INSTRUCTION_3 --> PREHEAT : LONG_PRESS
    note right of BACKFLUSH_INSTRUCTION_3 : steady

    BACKFLUSH_CYCLE_2 --> BACKFLUSH_CYCLE_DONE : counter reached
    BACKFLUSH_CYCLE_2 --> PREHEAT : LONG_PRESS
    note right of BACKFLUSH_CYCLE_2 : transient

    BACKFLUSH_CYCLE_DONE --> PREHEAT : SHORT_PRESS/LONG_PRESS
    note right of BACKFLUSH_CYCLE_DONE : steady

    PURGE_BEFORE_STEAM_1 --> PURGE_BEFORE_STEAM_2 : SHORT_PRESS
    PURGE_BEFORE_STEAM_1 --> PREHEAT : LONG_PRESS
    note right of PURGE_BEFORE_STEAM_1 : steady

    PURGE_BEFORE_STEAM_2 --> PURGE_BEFORE_STEAM_3 : timer expires
    PURGE_BEFORE_STEAM_2 --> PREHEAT : LONG_PRESS
    note right of PURGE_BEFORE_STEAM_2 : transient

    PURGE_BEFORE_STEAM_3 --> HEATING_TO_STEAM : SHORT_PRESS
    PURGE_BEFORE_STEAM_3 --> PREHEAT : LONG_PRESS
    note right of PURGE_BEFORE_STEAM_3 : steady

    GROUP_CLEAN_1 --> GROUP_CLEAN_2 : heater ≥ TARGET_HOT_WATER_DISPENSE_TEMP
    GROUP_CLEAN_1 --> PREHEAT : LONG_PRESS
    note right of GROUP_CLEAN_1 : transient

    SLEEP --> PREHEAT : SHORT_PRESS/LONG_PRESS
    note right of SLEEP : steady

    IGNORING_NETWORK --> PREHEAT : WiFi.off
    note right of IGNORING_NETWORK : transient

    note over SLEEP
      Entered from any state after inactivity timeout
    end note    
"""
