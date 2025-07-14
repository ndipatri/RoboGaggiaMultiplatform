package services

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import dev.bluefalcon.ApplicationContext

actual class SpeechToText actual constructor(private val context: ApplicationContext) {
    private var callback: ((String) -> Unit)? = null
    private val recognizer: SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context as android.content.Context).apply {
        setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle?) {
                results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.firstOrNull()?.let { callback?.invoke(it) }
            }

            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onError(error: Int) {}
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    actual fun startListening(onResult: (String) -> Unit) {
        callback = onResult
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }
        recognizer.startListening(intent)
    }

    actual fun stopListening() {
        recognizer.stopListening()
    }
}
