import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import theme.Typography

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ColumnScope.SingleFloatContent(stringResource: StringResource, value: Float) {
    Row(
        modifier = Modifier.Companion
            .weight(1f)
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        Text(
            style = Typography.body2,
            textAlign = TextAlign.Center,
            text = stringResource(stringResource, value)
        )
    }}

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ColumnScope.DoubleIntContent(stringResource: StringResource, value1: Int, value2: Int) {
    Row(
        modifier = Modifier.Companion
            .weight(1f)
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        Text(
            style = Typography.body2,
            textAlign = TextAlign.Center,
            text = stringResource(stringResource, value1, value2)
        )
    }
}