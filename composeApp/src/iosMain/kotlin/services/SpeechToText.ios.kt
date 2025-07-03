package services

import dev.bluefalcon.ApplicationContext
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioEngine
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryRecord
import platform.AVFAudio.AVAudioSessionModeMeasurement
import platform.AVFAudio.setActive
import platform.Speech.SFSpeechAudioBufferRecognitionRequest
import platform.Speech.SFSpeechRecognitionTask
import platform.Speech.SFSpeechRecognizer

@OptIn(ExperimentalForeignApi::class)
actual class SpeechToText actual constructor(private val context: ApplicationContext) {
    private val audioEngine = AVAudioEngine()
    private val recognizer = SFSpeechRecognizer()
    private var recognitionRequest: SFSpeechAudioBufferRecognitionRequest? = null
    private var recognitionTask: SFSpeechRecognitionTask? = null
    private var callback: ((String) -> Unit)? = null
    private var isListening = false

    actual fun startListening(onResult: (String) -> Unit) {
        if (isListening) {
            stopListening()
        }
        callback = onResult

        SFSpeechRecognizer.requestAuthorization { _ -> }

        val audioSession = AVAudioSession.sharedInstance()
        audioSession.setCategory(AVAudioSessionCategoryRecord, error = null)
        audioSession.setMode(AVAudioSessionModeMeasurement, error = null)
        audioSession.setActive(true, error = null)

        recognitionRequest = SFSpeechAudioBufferRecognitionRequest()
        val inputNode = audioEngine.inputNode
        val recordingFormat = inputNode.outputFormatForBus(0u)
        inputNode.removeTapOnBus(0u)
        inputNode.installTapOnBus(0u, bufferSize = 1024u, format = recordingFormat) { buffer, _ ->
            recognitionRequest?.appendAudioPCMBuffer(buffer!!)
        }

        audioEngine.prepare()
        audioEngine.startAndReturnError(null)

        recognitionTask = recognizer.recognitionTaskWithRequest(recognitionRequest!!) { result, _ ->
            result?.bestTranscription?.formattedString?.let { text ->
                callback?.invoke(text)
            }
        }
        isListening = true
    }

    actual fun stopListening() {
        if (!isListening) return
        audioEngine.stop()
        audioEngine.inputNode.removeTapOnBus(0u)
        recognitionRequest?.endAudio()
        recognitionTask?.cancel()
        recognitionRequest = null
        recognitionTask = null
        isListening = false
    }
}