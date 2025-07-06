package services

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
import kotlinx.io.Sink
import kotlinx.io.Source
import io.modelcontextprotocol.kotlin.sdk.CallToolResult
import io.modelcontextprotocol.kotlin.sdk.TextContent


class MCPManager(val apiKey: String, val coroutineScope: CoroutineScope) {
    // Configures using the `ANTHROPIC_API_KEY` and `ANTHROPIC_AUTH_TOKEN` environment variables

    // Initialize MCP client
    private val client: Client =
        Client(clientInfo = Implementation(name = "mcp-client-cli", version = "1.0.0"))

    // Use Buffer directly for in-memory transport
    private val serverToClientBuffer = Buffer()
    private val clientToServerBuffer = Buffer()

    val serverInput: Source = clientToServerBuffer
    val serverOutput: Sink = serverToClientBuffer
    val clientInput: Source = serverToClientBuffer
    val clientOutput: Sink = clientToServerBuffer

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
                description = "provide the first name given the last name"
            ) { request ->
                // Extract the lastName parameter from the request
                val lastName =
                    "Smith" // For now, use a hardcoded value since request structure needs investigation

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
                    ) // Use TextContent instead of empty list
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
                val tools = client.listTools()
                if (tools != null) {
                    println("*** NJD: Available tools from server:")
                    tools.tools.forEach { tool ->
                        println("  - Tool: ${tool.name}")
                        println("    Description: ${tool.description}")
                        tool.inputSchema?.let { schema ->
                            println("    Input Schema: $schema")
                        }
                    }
                    onSuccess("*** NJD: Connected to MCP server with ${tools.tools.size} available tools")
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