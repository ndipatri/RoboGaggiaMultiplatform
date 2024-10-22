package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import content.ScreenContent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import robogaggiamultiplatform.composeapp.generated.resources.*
import services.SettingsViewModel

@Composable
fun SettingsScreen(
    onExitClicked: () -> Unit
) {
    ScreenContent(
        button2Resource = Res.string.exit,
        onSecondButtonClick = onExitClicked
    ) {
        KoinContext {
            val settingsViewModel = koinInject<SettingsViewModel>().also {
                it.initialize()
            }
            val settingsState = settingsViewModel.settingsUIStateFlow.collectAsState()

            SettingsContent(settingsState = settingsState,
                onSettingsSave = {
                    settingsViewModel.saveSettings(it)
                }
            )
        }
    }
}

@Composable
private fun SettingsContent(
    settingsState: State<SettingsViewModel.SettingsState>,
    onSettingsSave: (SettingsViewModel.SettingsState) -> Unit
) {
    // intrinsic state here is the new settings state that is being updated
    // by SettingsControls...
    val newSettingsState = remember(settingsState) { mutableStateOf(settingsState.value) }
    Column {
        SettingsControls(newSettingsState.value,
            onSettingsStateChange = {
                newSettingsState.value = it
            }
        )

        Button(
            enabled = newSettingsState.value.formState == SettingsViewModel.FormState.Success,
            onClick = {
                onSettingsSave(newSettingsState.value)
            }
        ) {
            Text(stringResource(Res.string.save))
        }
    }
}

@Composable
private fun SettingsControls(
    settingsState: SettingsViewModel.SettingsState,
    onSettingsStateChange: (SettingsViewModel.SettingsState) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.padding(top = 50.dp),
                textAlign = TextAlign.Left,
                text = stringResource(Res.string.cup_weight)
            )

            // intrinsic state is just the values for each settings controller
            var cupWeightSliderValue by remember(settingsState) {
                mutableStateOf(
                    settingsState.referenceCupWeight.toSliderValue()
                )
            }
            Slider(
                enabled = settingsState.formState == SettingsViewModel.FormState.Success,
                value = cupWeightSliderValue,

                // begin slide action.. we only update intrinsic state
                onValueChange = {
                    cupWeightSliderValue = it
                },

                // now we hoist our state
                onValueChangeFinished = {
                    // for now, assume weight is 0 to 100 grams
                    onSettingsStateChange(settingsState.copy(referenceCupWeight = cupWeightSliderValue.fromSliderValue()))
                }
            )
        }
    }
}

// For now, assume Slider range of 0 to 1F corresponds to 0 to 100 grams.
fun Int.toSliderValue() = this / 100.0f
fun Float.fromSliderValue() = (this * 100.0f).toInt()

