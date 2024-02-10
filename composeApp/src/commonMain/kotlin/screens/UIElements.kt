package screens

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * This button will have a very short delay before calling 'onClick()' while it shows a UI
 * artifact of a grey box around the content
 */
@Composable
fun DelayedClickButton(onClick: () -> Unit, content: @Composable RowScope.() -> Unit) {
    var showBackground by remember { mutableStateOf(false) }

    // Returns a scope that's cancelled when removed from composition
    val coroutineScope = rememberCoroutineScope()

    Button(
        onClick = {
            coroutineScope.launch {
                showBackground = true
                delay(200)
                showBackground = false
                onClick.invoke()
            }
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = if (showBackground) Color.DarkGray else Color.Transparent),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        ),
        shape =  RoundedCornerShape(20.dp),
        content = content
    )
}