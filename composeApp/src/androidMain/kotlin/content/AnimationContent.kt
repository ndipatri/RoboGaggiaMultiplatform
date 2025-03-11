package content

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import app.rive.runtime.kotlin.RiveAnimationView
import app.rive.runtime.kotlin.core.Alignment
import com.ndipatri.roboaggia.R

@Composable
actual fun SleepAnimation(modifier: Modifier) = CustomRiveAnimationView(
    modifier = modifier,
    animation = R.raw.nick
)


@Composable
fun CustomRiveAnimationView(
    modifier: Modifier = Modifier,
    @RawRes animation: Int,
    stateMachineName: String? = null,
    alignment: Alignment = Alignment.CENTER
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            RiveAnimationView(context).also {
                it.setRiveResource(
                    resId = animation,
                    stateMachineName = stateMachineName,
                    alignment = alignment
                )
            }
        }
    )
}