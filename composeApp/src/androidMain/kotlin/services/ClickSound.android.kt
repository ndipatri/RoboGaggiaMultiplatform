package services

import android.media.AudioManager
import android.media.ToneGenerator
import dev.bluefalcon.ApplicationContext

actual class ClickSound actual constructor(context: ApplicationContext) {
    private val toneGenerator = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100)

    actual fun play() {
        toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 150)
    }
}
