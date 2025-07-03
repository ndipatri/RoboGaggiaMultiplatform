package services

import dev.bluefalcon.ApplicationContext

/**
 * Simple cross-platform speech recognition service.
 */
expect class SpeechToText(context: ApplicationContext) {
    /** Start listening and return partial or final results via [onResult]. */
    fun startListening(onResult: (String) -> Unit)

    /** Stop listening for speech input. */
    fun stopListening()
}
