package services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndipatri.kmp.openapi.particle.apis.DefaultApi
import com.ndipatri.robogaggia.BuildKonfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel: ViewModel() {

    val authenticatedApi = DefaultApi().apply {
        setBearerToken(BuildKonfig.PARTICLE_ACCESS_TOKEN)
    }

    val settingsUIStateFlow = MutableStateFlow(
        SettingsState(
            referenceCupWeight = -1, // value is irrelevant in Init state
            formState = FormState.Init
        )
    )

    fun initialize() {
        viewModelScope.launch {
           loadSettings()
        }
    }

    fun saveSettings(newSettingsState: SettingsState) {

        settingsUIStateFlow.update {
            it.copy(formState = FormState.Loading)
        }

        viewModelScope.launch {
            // for now, it's just cup weight
            if (setReferenceCupWeight(newSettingsState.referenceCupWeight)) {
                settingsUIStateFlow.update {
                    it.copy(referenceCupWeight = newSettingsState.referenceCupWeight,
                            formState = FormState.Success)
                }
            } else {
                settingsUIStateFlow.update {
                    it.copy(formState = FormState.Error)
                }
            }
        }
    }

    private suspend fun loadSettings() {
        val referenceCupWeight = getReferenceCupWeight()

        // for now, it's just cup weight
        settingsUIStateFlow.update { oldState ->
            oldState.copy(referenceCupWeight = referenceCupWeight, formState = FormState.Success)
        }
    }

    private suspend fun setReferenceCupWeight(cupWeight: Int): Boolean {
        authenticatedApi.getDevices().body().filter {
            // NJD TODO - Need to get this value dynamically via telemetry from Gaggia
            it.name.equals("roboGaggia3")
        }.first().let { device ->
            val result = authenticatedApi.callFunction(
                deviceId = device.id!!,
                functionName = "setReferenceCupWeight",
                requestBody = mapOf("arg" to cupWeight.toString(), "format" to "string"),
                productIdOrSlug = "",
            )

            return result.success &&
                   result.body().returnValue == 1
        }
    }

    private suspend fun getReferenceCupWeight(): Int {
        return authenticatedApi.getDevices().body().first {
            // NJD TODO - Need to get this value dynamically via telemetry from Gaggia
            it.name.equals("roboGaggia3")
        }.let { device ->
            authenticatedApi.getVariableValue(
                deviceId = device.id!!,
                varName = "referenceCupWeight",
                productIdOrSlug = "",
            ).body().result?.toInt() ?: -1
        }
    }

    data class SettingsState(
        val referenceCupWeight: Int,
        val formState: FormState
    )

    sealed interface FormState {
        data object Init : FormState
        data object Loading : FormState
        data object Success : FormState
        data object Error : FormState
    }
}