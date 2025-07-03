package services

import dev.bluefalcon.ApplicationContext
import platform.AVFoundation.AVAudioEngine
import platform.AVFoundation.AVAudioSession
import platform.Speech.SFSpeechAudioBufferRecognitionRequest
import platform.Speech.SFSpeechRecognitionTask
import platform.Speech.SFSpeechRecognizer
import platform.Speech.SFSpeechRecognizerAuthorizationStatus
import platform.Speech.requestAuthorization

actual class SpeechToText actual constructor(private val context: ApplicationContext) {
    private val audioEngine = AVAudioEngine()
    private val recognizer = SFSpeechRecognizer()
    private var recognitionRequest: SFSpeechAudioBufferRecognitionRequest? = null
    private var recognitionTask: SFSpeechRecognitionTask? = null
    private var callback: ((String) -> Unit)? = null

    actual fun startListening(onResult: (String) -> Unit) {
        callback = onResult
        requestAuthorization { }

        val audioSession = AVAudioSession.sharedInstance()
        audioSession.setCategory("record")
        audioSession.setMode("measurement")
        audioSession.setActive(true, null)

        recognitionRequest = SFSpeechAudioBufferRecognitionRequest()
        val inputNode = audioEngine.inputNode
        val recordingFormat = inputNode.outputFormatForBus(0)
        inputNode.installTapOnBus(0, 1024, recordingFormat) { buffer, _ ->
            recognitionRequest?.appendAudioPCMBuffer(buffer)
        }

        audioEngine.prepare()
        audioEngine.startAndReturnError(null)

        recognitionTask = recognizer.recognitionTaskWithRequest(recognitionRequest!!, resultHandler = { result, _ ->
            result?.bestTranscription?.formattedString?.let { text ->
                callback?.invoke(text)
            }
        })
    }

    actual fun stopListening() {
        audioEngine.stop()
        recognitionRequest?.endAudio()
        recognitionTask?.cancel()
    }
}
