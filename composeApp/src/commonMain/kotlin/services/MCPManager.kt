package services

import com.xemantic.ai.anthropic.Anthropic
import com.xemantic.ai.anthropic.content.Text
import com.xemantic.ai.anthropic.content.ToolUse
import com.xemantic.ai.anthropic.message.Message
import com.xemantic.ai.anthropic.message.StopReason
import com.xemantic.ai.anthropic.tool.Tool
import com.xemantic.ai.tool.schema.meta.Description
import currentTimeMillis
import io.modelcontextprotocol.kotlin.sdk.TextContent
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
    val terminalString: String,
    val timestamp: Long = currentTimeMillis()
)