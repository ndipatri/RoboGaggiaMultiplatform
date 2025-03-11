import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.window.ComposeUIViewController
import content.NativeViewFactory
import di.initKoin
import platform.UIKit.UIView

val LocalNativeViewFactory = staticCompositionLocalOf<NativeViewFactory> {
    error("No view factory provided.")
}

fun MainViewController(nativeViewFactory: NativeViewFactory) = ComposeUIViewController(
    configure = {
        initKoin()

        // NJD TODO - need to initialize Rive here!
    }
) {
    CompositionLocalProvider(LocalNativeViewFactory provides nativeViewFactory) {
        App(true)
    }
}