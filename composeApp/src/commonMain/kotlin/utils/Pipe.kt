package utils

import okio.Buffer
import okio.Sink
import okio.Source
import okio.Timeout

/**
 * Minimal pipe implementation to replace the removed okio.Pipe. This is not
 * a fully featured substitute but is sufficient for local in-memory
 * transports used by [MCPManager].
 */
class Pipe(maxBufferSize: Int) {
    private val buffer = Buffer()

    val sink: Sink = object : Sink {
        override fun write(source: Buffer, byteCount: Long) {
            buffer.write(source, byteCount)
        }

        override fun flush() {}
        override fun timeout(): Timeout = Timeout.NONE
        override fun close() {}
    }

    val source: Source = object : Source {
        override fun read(sink: Buffer, byteCount: Long): Long {
            return if (buffer.size == 0L) {
                -1L
            } else {
                buffer.read(sink, byteCount.coerceAtMost(buffer.size))
            }
        }

        override fun timeout(): Timeout = Timeout.NONE
        override fun close() {}
    }
}
