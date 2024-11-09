package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import robogaggiamultiplatform.composeapp.generated.resources.Res
import robogaggiamultiplatform.composeapp.generated.resources.cup_weight
import robogaggiamultiplatform.composeapp.generated.resources.dark_circuitboard
import robogaggiamultiplatform.composeapp.generated.resources.exit
import robogaggiamultiplatform.composeapp.generated.resources.problems_loading_settings
import robogaggiamultiplatform.composeapp.generated.resources.save
import robogaggiamultiplatform.composeapp.generated.resources.weight_to_bean_ratio
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(Res.drawable.dark_circuitboard),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            SettingsContent(
                settings = settingsState.value,
                onExitClicked = onExitClicked,
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
    onExitClicked: () -> Unit,
    onSettingsSave: (SettingsViewModel.SettingsState) -> Unit
) {
    // intrinsic state here is the new settings state that is being updated
    // by SettingsControls... so this state is both intrinsic and hoisted
    var newSettingsState by remember(settings) {
        mutableStateOf(settings)
    }
    var settingsChanged by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(.20F)
                .padding(horizontal = 10.dp)
        ) {
            OutlinedButton(
                onClick = { onExitClicked() },
                modifier = Modifier.requiredSize(150.dp).weight(.5f).padding(20.dp),
                shape = CircleShape,
                border = BorderStroke(5.dp, Color(0XFF0F9D58)),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = androidx.compose.ui.graphics.Color.White,
                    backgroundColor = androidx.compose.ui.graphics.Color.Black
                )
            ) {
                Text(
                    text = stringResource(Res.string.exit),
                    textAlign = TextAlign.Center
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(.60F)
        ) {
            SettingsControls(
                settingsState = newSettingsState,
                onSettingsStateChange = {
                    newSettingsState = it
                    settingsChanged = true
                },
                onValidationChange = {
                    isValid = it
                },
                modifier = Modifier.weight(1f)
            )
        }

        Column(
            modifier = Modifier.fillMaxHeight().weight(.20F)
        ) {
            val okToSave =
                newSettingsState.submissionState == SettingsViewModel.SubmissionState.Success &&
                        isValid &&
                        settingsChanged

            if (okToSave) {
                OutlinedButton(
                    onClick = { onSettingsSave(newSettingsState) },
                    enabled = okToSave,
                    modifier = Modifier.requiredSize(150.dp).weight(.5f).padding(20.dp),
                    shape = CircleShape,
                    border = BorderStroke(5.dp, Color(0XFF0F9D58)),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = androidx.compose.ui.graphics.Color.White,
                        backgroundColor = androidx.compose.ui.graphics.Color.Black
                    )
                ) {
                    Text(
                        text = stringResource(Res.string.save),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SettingsControls(
    settingsState: SettingsViewModel.SettingsState,
    onSettingsStateChange: (SettingsViewModel.SettingsState) -> Unit,
    onValidationChange: (Boolean) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (settingsState.submissionState) {

            SettingsViewModel.SubmissionState.Loading, SettingsViewModel.SubmissionState.Init -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(200.dp),
                    color = Color.White,
                    strokeWidth = 10.dp,
                )
            }

            SettingsViewModel.SubmissionState.Error -> {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(Res.string.problems_loading_settings),
                    style = Typography.body1
                )
            }

            SettingsViewModel.SubmissionState.Success -> {
                    var referenceCupWeightValue by remember(settingsState) {
                        mutableStateOf(
                            settingsState.referenceCupWeight.toString()
                        )
                    }
                    SettingsRow(
                        value = referenceCupWeightValue,
                        labelResource = Res.string.cup_weight,
                        isError = !referenceCupWeightValue.isValidCupWeight(),
                        errorMessage = "Must be at least 10g and less than 200g",
                        onValueChange = {
                            referenceCupWeightValue = it
                            onValidationChange.invoke(it.isValidCupWeight())
                            if (it.isValidCupWeight()) {
                                onSettingsStateChange(
                                    settingsState.copy(
                                        referenceCupWeight = it.toInt(),
                                    )
                                )
                            }
                        }
                    )


                    var weightToBeanRatioValue by remember(settingsState) {
                        mutableStateOf(
                            settingsState.weightToBeanRatio.toString()
                        )
                    }
                    SettingsRow(
                        value = weightToBeanRatioValue,
                        labelResource = Res.string.weight_to_bean_ratio,
                        isError = !weightToBeanRatioValue.isValidWeightToBeanRatio(),
                        errorMessage = "Must be between 1 and 4",
                        onValueChange = {
                            weightToBeanRatioValue = it
                            onValidationChange.invoke(it.isValidWeightToBeanRatio())
                            if (it.isValidWeightToBeanRatio()) {
                                onSettingsStateChange(
                                    settingsState.copy(
                                        weightToBeanRatio = it.toInt(),
                                    )
                                )
                            }
                        }
                    )
            }
        }
    }
}

@Composable
private fun ColumnScope.SettingsRow(
    value: String,
    labelResource: StringResource,
    isError: Boolean,
    errorMessage: String,
    onValueChange: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {

        OutlinedTextField(
            value = value,
            label = {
                Text(
                    text = stringResource(labelResource),
                    style = Typography.body2,
                    modifier = Modifier.padding(20.dp)
                )
            },
            textStyle = Typography.body2,
            isError = isError,
            placeholder = {
                if (isError) Text(errorMessage)
            },
            onValueChange = {
                onValueChange(it)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Purple40,
                backgroundColor = Color.Black,
                placeholderColor = Color.White
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier.padding(20.dp)
        )
    }
}

fun String.isValidCupWeight() = this.toIntOrNull() in 10..200
fun String.isValidWeightToBeanRatio() = this.toIntOrNull() in 1..4
