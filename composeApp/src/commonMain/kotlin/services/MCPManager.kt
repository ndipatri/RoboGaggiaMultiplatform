package services

import currentTimeMillis
import io.modelcontextprotocol.kotlin.sdk.Implementation
import io.modelcontextprotocol.kotlin.sdk.ServerCapabilities
import io.modelcontextprotocol.kotlin.sdk.client.Client
import io.modelcontextprotocol.kotlin.sdk.client.StdioClientTransport
import io.modelcontextprotocol.kotlin.sdk.server.Server
import io.modelcontextprotocol.kotlin.sdk.server.ServerOptions
import io.modelcontextprotocol.kotlin.sdk.server.StdioServerTransport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.io.Buffer
import kotlinx.io.RawSink
import kotlinx.io.RawSource
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.buffered
import io.modelcontextprotocol.kotlin.sdk.CallToolResult
import io.modelcontextprotocol.kotlin.sdk.ListToolsResult
import io.modelcontextprotocol.kotlin.sdk.TextContent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import com.xemantic.ai.anthropic.Anthropic
import com.xemantic.ai.anthropic.message.Message
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive

class MCPManager(val apiKey: String, val coroutineScope: CoroutineScope) {
    // Configures using the `ANTHROPIC_API_KEY` and `ANTHROPIC_AUTH_TOKEN` environment variables

    // Initialize MCP client
    private val client: Client =
        Client(clientInfo = Implementation(name = "mcp-client-cli", version = "1.0.0"))

    // Use separate buffers for bidirectional communication
    private val serverToClientPipe = BlockingPipe()
    private val clientToServerPipe = BlockingPipe()

    // Server reads from clientToServerBuffer, writes to serverToClientBuffer
    val serverInput: Source = clientToServerPipe.source
    val serverOutput: Sink = serverToClientPipe.sink

    // Client reads from serverToClientBuffer, writes to clientToServerBuffer  
    val clientInput: Source = serverToClientPipe.source
    val clientOutput: Sink = clientToServerPipe.sink

    private var availableTools: ListToolsResult? = null

    suspend fun executeQuery(query: String): String {
        return try {
            // Create Anthropic client
            val anthropic = Anthropic {
                apiKey = this@MCPManager.apiKey
            }

            println("*** NJD: Executing query against Claude: $query using API Key: $apiKey")

            // Try a direct API call approach
            val response = anthropic.messages.create {
                model = "claude-3-5-sonnet-20241022"
                maxTokens = 1000
                +Message {
                    +query
                }
            }

            println("*** NJD: Received response from Claude: $response")

            response.toString()

        } catch (e: Exception) {
            println("*** NJD: Error executing query: ${e.message}")
            e.printStackTrace()
            "Error executing query: ${e.message}"
        }
    }

    suspend fun startMCP(
        onSuccess: (String) -> Unit,
        onServerDisconnect: () -> Unit,
    ) {
        lateinit var server: Server
        try {
            server = Server(
                Implementation(
                    name = "RoboGaggia MCP Server",
                    version = "1.0.0"
                ),
                ServerOptions(
                    capabilities = ServerCapabilities(
                        tools = ServerCapabilities.Tools(listChanged = true)
                    )
                )
            )

            // Add the first name tool
            server.addTool(
                name = "get_first_name",
                description = "provide the first name given the last name",
                inputSchema = io.modelcontextprotocol.kotlin.sdk.Tool.Input(
                    properties = JsonObject(
                        mapOf(
                            "last_name" to JsonObject(
                                mapOf(
                                    "type" to JsonPrimitive("string"),
                                    "description" to JsonPrimitive("The last name to look up the corresponding first name")
                                )
                            )
                        )
                    ),
                    required = listOf("last_name")
                )
            ) { request ->
                // Extract the lastName parameter from the request
                val lastName = request.arguments["last_name"]?.jsonPrimitive?.content ?: "Unknown"

                // Simple lookup logic
                val firstName = when (lastName) {
                    "Smith" -> "John"
                    "Johnson" -> "Emily"
                    "Williams" -> "Michael"
                    "Brown" -> "Sarah"
                    "Jones" -> "David"
                    else -> "Unknown"
                }

                // Return CallToolResult with proper content
                CallToolResult(
                    content = listOf(
                        TextContent(
                            text = "First name for $lastName is $firstName"
                        )
                    )
                )
            }

            val transport = StdioServerTransport(
                inputStream = serverInput,
                outputStream = serverOutput
            )

            println("*** NJD: connecting to server transport")

            // the coroutine running this must stay alive in order
            // to continue processing messages to and from MCPServer
            server.connect(transport)
            server.onClose {
                println("*** NJD: Server closed")
                onServerDisconnect()
            }
        } catch (e: Exception) {
            println("*** NJD: Failed to connect to MCP server: $e")
            server.close()
            throw e
        }

        delay(1000)
        try {
            val transport = StdioClientTransport(
                input = clientInput,
                output = clientOutput
            )

            println("*** NJD: client connecting to server...")
            client.connect(transport)

            // Get list of available tools from the server
            try {
                availableTools = client.listTools()

                if (availableTools != null) {
                    println("*** NJD: Available tools from server:")
                    availableTools!!.tools.forEach { tool ->
                        println("  - Tool: ${tool.name}")
                        println("    Description: ${tool.description}")
                        tool.inputSchema?.let { schema ->
                            println("    Input Schema: $schema")
                        }
                    }
                    onSuccess("*** NJD: Connected to MCP server with ${availableTools!!.tools.size} available tools")
                } else {
                    println("*** NJD: No tools response received")
                    onSuccess("*** NJD: Connected to MCP server but received no tools response")
                }
            } catch (e: Exception) {
                println("*** NJD: Failed to list tools: $e")
                onSuccess("*** NJD: Connected to MCP server but failed to list tools")
            }

        } catch (e: Exception) {
            println("*** NJD: Failed to start MCP: $e")
            client.close()
            throw e
        }
    }
}

data class MCPQuery(
    val queryString: String,
    val terminalString: String,
    val timestamp: Long = currentTimeMillis()
)

/**
 * A simple pipe implementation that uses channels for proper blocking behavior
 */
class BlockingPipe {
    private val channel = Channel<ByteArray>(Channel.UNLIMITED)

    val source: Source = object : RawSource {
        override fun readAtMostTo(sink: Buffer, byteCount: Long): Long {
            return runBlocking {
                try {
                    val data = channel.receive()
                    val bytesToWrite = minOf(data.size.toLong(), byteCount)
                    sink.write(data, 0, bytesToWrite.toInt())
                    bytesToWrite
                } catch (e: Exception) {
                    -1L // EOF
                }
            }
        }

        override fun close() {
            channel.close()
        }
    }.buffered()

    val sink: Sink = object : RawSink {
        override fun write(source: Buffer, byteCount: Long) {
            val bytes = ByteArray(byteCount.toInt())
            source.readAtMostTo(bytes, 0, byteCount.toInt())
            runBlocking {
                channel.send(bytes)
            }
        }

        override fun flush() {
            // No-op for channel-based implementation
        }

        override fun close() {
            channel.close()
        }
    }.buffered()
}