package content

import LocalNativeViewFactory
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitViewController

@Composable
actual fun WelcomeAnimation(modifier: Modifier) {
    val factory = LocalNativeViewFactory.current

    UIKitViewController(
        modifier = modifier
            .width(100.dp)
            .height(50.dp),
        factory = {
            factory.renderWelcomeAnimation()
        }
    )
}
