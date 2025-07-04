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

class MCPManager(val apiKey: String, val coroutineScope: CoroutineScope) {
    // Configures using the `ANTHROPIC_API_KEY` and `ANTHROPIC_AUTH_TOKEN` environment variables

    // Initialize MCP client
    private val client: Client =
        Client(clientInfo = Implementation(name = "mcp-client-cli", version = "1.0.0"))

    private val serverToClient = Pipe(8 * 1024)
    private val clientToServer = Pipe(8 * 1024)

    val serverInput = clientToServer.source
    val serverOutput = serverToClient.sink
    val clientInput = serverToClient.source
    val clientOutput = clientToServer.sink

    suspend fun startMCP(
        onSuccess: (String) -> Unit,
        onServerDisconnect: () -> Unit,
    ) {
        lateinit var server: Server
        try {
            server = Server(
                Implementation(
                    name = "Xfinity MCP Server",
                    version = "1.0.0"
                ),
                ServerOptions(
                    capabilities = ServerCapabilities(
                        tools = ServerCapabilities.Tools(listChanged = true)
                    )
                )
            )

            val transport = StdioServerTransport(
                inputStream = serverInput.buffered(),
                outputStream = serverOutput.buffered()
            )

            println("*** NJD: connecting to server transport")

            // the coroutine running this must stay alive in order
            // to continue processing messages to and from MCPServer
            server.connect(transport)
            server.onClose {
                println("Server closed")
                onServerDisconnect()
            }
        } catch (e: Exception) {
            println("Failed to connect to MCP server: $e")
            server.close()
            throw e
        }

        delay(1000)
        try {
            val transport = StdioClientTransport(
                input = clientInput.buffered(),
                output = clientOutput.buffered()
            )

            println("*** NJD: client connecting to server...")
            client.connect(transport)

        } catch (e: Exception) {
            println("Failed to start MCP: $e")
            client.close()
            throw e
        }
    }
}