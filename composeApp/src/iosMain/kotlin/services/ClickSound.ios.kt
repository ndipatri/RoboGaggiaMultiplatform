package services

import dev.bluefalcon.ApplicationContext
import platform.AudioToolbox.AudioServicesPlaySystemSound

actual class ClickSound actual constructor(context: ApplicationContext) {
    actual fun play() {
        // 1104 is the 'Tock' system sound
        AudioServicesPlaySystemSound(1104u)
    }
}
