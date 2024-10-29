package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.cup_weight
import robogaggiamultiplatform.composeapp.generated.resources.dark_circuitboard
import robogaggiamultiplatform.composeapp.generated.resources.save
import services.SettingsViewModel
import theme.Purple40
import theme.Typography

@Composable
fun SettingsScreen(
    onExitClicked: () -> Unit
) {
    KoinContext {
        val settingsViewModel = koinInject<SettingsViewModel>().apply {
            // Presumably this will trigger our settingsState to be in
            // an Init state until settings are loaded from the network
            loadSettings()
        }
        val settingsState = settingsViewModel.settingsUIStateFlow.collectAsState()

        Box(modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(Res.drawable.dark_circuitboard),
                   contentScale = ContentScale.FillBounds)
        ) {
            SettingsContent(
                settings = settingsState.value,
                onSettingsSave = {
                    settingsViewModel.saveSettings(it)
                }
            )
        }
    }
}

@Composable
fun SettingsContent(
    settings: SettingsViewModel.SettingsState,
    onSettingsSave: (SettingsViewModel.SettingsState) -> Unit
) {
    // intrinsic state here is the new settings state that is being updated
    // by SettingsControls... so this state is both intrinsic and hoisted
    var newSettingsState by remember(settings) {
        mutableStateOf(settings)
    }
    var settingsChanged by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        SettingsControls(
            settingsState = newSettingsState,
            onSettingsStateChange = {
                newSettingsState = it
                settingsChanged = true
            }
        )

        val okToSave = newSettingsState.formState == SettingsViewModel.FormState.Success &&
                settingsChanged
        Button(
            enabled = okToSave,
            onClick = {
                onSettingsSave(newSettingsState)
            }
        ) {
            Text(
                text = stringResource(Res.string.save),
                style = Typography.body1
            )
        }
    }
}

@Preview
@Composable
private fun SettingsControls(
    settingsState: SettingsViewModel.SettingsState,
    onSettingsStateChange: (SettingsViewModel.SettingsState) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(Res.string.cup_weight),
                style = Typography.body1,
                modifier = Modifier.width(300.dp).padding(20.dp)
            )

            // intrinsic state is just the values for each settings controller
            var cupWeightSliderValue by remember(settingsState) {
                mutableStateOf(
                    settingsState.referenceCupWeight.toSliderValue()
                )
            }

            Text(
                textAlign = TextAlign.Left,
                text = "${cupWeightSliderValue.fromSliderValue()} g",
                style = Typography.body2,
                modifier = Modifier.padding(20.dp)
            )

            Slider(
                enabled = settingsState.formState == SettingsViewModel.FormState.Success,
                value = cupWeightSliderValue,

                steps = 100,

                // begin slide action.. we only update intrinsic state
                onValueChange = {
                    cupWeightSliderValue = it
                },

                // now we hoist our state
                onValueChangeFinished = {
                    // for now, assume weight is 0 to 100 grams
                    onSettingsStateChange(settingsState.copy(referenceCupWeight = cupWeightSliderValue.fromSliderValue()))
                },

                colors = SliderDefaults.colors(thumbColor = Purple40, activeTrackColor = Color.White, inactiveTrackColor = Color.White),

                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

// For now, assume Slider range of 0 to 1F corresponds to 0 to 100 grams.
fun Int.toSliderValue() = this / 100.0f
fun Float.fromSliderValue() = (this * 100.0f).toInt()

