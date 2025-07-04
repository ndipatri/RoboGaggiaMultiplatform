package services

import dev.bluefalcon.ApplicationContext
import com.ndipatri.robogaggia.BuildKonfig
import io.modelcontextprotocol.kotlin.sdk.Implementation
import io.modelcontextprotocol.kotlin.sdk.ServerCapabilities
import io.modelcontextprotocol.kotlin.sdk.client.Client
import io.modelcontextprotocol.kotlin.sdk.client.ClientOptions
import io.modelcontextprotocol.kotlin.sdk.client.StdioClientTransport
import io.modelcontextprotocol.kotlin.sdk.server.Server
import io.modelcontextprotocol.kotlin.sdk.server.ServerOptions
import io.modelcontextprotocol.kotlin.sdk.server.StdioServerTransport
import kotlinx.io.InputStreamSource
import kotlinx.io.OutputStreamSink
import java.io.PipedInputStream
import java.io.PipedOutputStream

actual class MCPService actual constructor(context: ApplicationContext) {
    private val clientTransport: StdioClientTransport
    private val serverTransport: StdioServerTransport
    val client: Client
    val server: Server
    private val anthropicApiKey = BuildKonfig.ANTHROPIC_API_KEY

    init {
        val clientToServer = PipedOutputStream()
        val serverInput = PipedInputStream(clientToServer)

        val serverToClient = PipedOutputStream()
        val clientInput = PipedInputStream(serverToClient)

        clientTransport = StdioClientTransport(
            InputStreamSource(clientInput),
            OutputStreamSink(clientToServer)
        )
        serverTransport = StdioServerTransport(
            InputStreamSource(serverInput),
            OutputStreamSink(serverToClient)
        )

        server = Server(
            Implementation("MCPServer", "0.1"),
            ServerOptions(ServerCapabilities(), false)
        )
        client = Client(
            Implementation("MCPClient", "0.1"),
            ClientOptions()
        )
    }

    suspend fun start() {
        serverTransport.start()
        server.connect(serverTransport)
        clientTransport.start()
        client.connect(clientTransport)
    }
}
