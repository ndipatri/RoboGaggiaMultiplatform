import androidx.compose.ui.window.ComposeUIViewController
import di.initKoin
import platform.UIKit.UIView

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App(true)
}