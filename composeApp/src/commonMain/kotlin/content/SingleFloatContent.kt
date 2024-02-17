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
import utils.toStringWithTenths

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ColumnScope.SingleFloatContent(stringResource: StringResource, value: Float) {

    // NJD TODO - Normally would do:
    // stringResource(stringResource, value)
    // but this doesn't see to work with latest Compose KMP:
    // https://github.com/JetBrains/compose-multiplatform/releases/tag/v1.6.0-rc02

    val textString = stringResource(stringResource).replace("%.1f", value.toStringWithTenths())

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
            text = textString
        )
    }}

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ColumnScope.DoubleIntContent(stringResource: StringResource, value1: Int, value2: Int) {

    // NJD TODO - Normally would do:
    // stringResource(stringResource, value)
    // but this doesn't see to work with latest Compose KMP:
    // https://github.com/JetBrains/compose-multiplatform/releases/tag/v1.6.0-rc02

    val textString = stringResource(stringResource)
                            .replace("%1\$d", value1.toString())
                            .replace("%2\$d", value2.toString())
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
            text = textString
        )
    }
}