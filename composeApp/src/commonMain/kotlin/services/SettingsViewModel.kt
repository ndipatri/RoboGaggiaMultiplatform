package services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndipatri.kmp.openapi.particle.apis.DefaultApi
import com.ndipatri.robogaggia.BuildKonfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    val authenticatedApi = DefaultApi().apply {
        setBearerToken(BuildKonfig.PARTICLE_ACCESS_TOKEN)
    }

    val settingsUIStateFlow = MutableStateFlow(
        SettingsState(
            referenceCupWeight = -1, // value is irrelevant in Init state
            weightToBeanRatio = -1, // value is irrelevant in Init state
            submissionState = SubmissionState.Init
        )
    )

    fun loadSettings() {
        viewModelScope.launch {
            try {
                val referenceCupWeight = getReferenceCupWeight()
                val weightToBeanRatio = getWeightToBeanRatio()

                // for now, it's just cup weight
                settingsUIStateFlow.update { oldState ->
                    oldState.copy(
                        referenceCupWeight = referenceCupWeight,
                        weightToBeanRatio = weightToBeanRatio,
                        submissionState = SubmissionState.Success
                    )
                }
            } catch (ex: Exception) {
                settingsUIStateFlow.update { oldState ->
                    oldState.copy(
                        submissionState = SubmissionState.Error
                    )
                }
            }
        }
    }

    fun saveSettings(newSettingsState: SettingsState) {

        settingsUIStateFlow.update {
            it.copy(submissionState = SubmissionState.Loading)
        }

        viewModelScope.launch {
            try {
                setReferenceCupWeight(newSettingsState.referenceCupWeight)
                setWeightToBeanRatio(newSettingsState.weightToBeanRatio)

                settingsUIStateFlow.update {
                    it.copy(
                        referenceCupWeight = newSettingsState.referenceCupWeight,
                        weightToBeanRatio = newSettingsState.weightToBeanRatio,
                        submissionState = SubmissionState.Success
                    )
                }
            } catch (ex: Exception) {
                settingsUIStateFlow.update { oldState ->
                    oldState.copy(
                        submissionState = SubmissionState.Error
                    )
                }
            }
        }
    }

    private suspend fun setReferenceCupWeight(cupWeight: Int) =
        setParticleVariable("setReferenceCupWeight", cupWeight.toString())

    private suspend fun setWeightToBeanRatio(weightToBeanRatio: Int) =
        setParticleVariable("setWeightToBeanRatio", weightToBeanRatio.toString())

    private suspend fun getReferenceCupWeight() =
        getParticleVariable("referenceCupWeight")

    private suspend fun getWeightToBeanRatio() =
        getParticleVariable("weightToBeanRatio")

    private suspend fun setParticleVariable(varName: String, varValue: String) {
        authenticatedApi.getDevices().body().filter {
            // NJD TODO - Need to get this value dynamically via telemetry from Gaggia
            it.name.equals("roboGaggia3")
        }.first().let { device ->
            val response = authenticatedApi.callFunction(
                deviceId = device.id!!,
                functionName = varName,
                requestBody = mapOf("arg" to varValue, "format" to "string"),
                productIdOrSlug = "",
            )

            if (!response.success || response.body().returnValue != 1) {
                throw Exception("Failed to set reference cup weight")
            }
        }
    }

    private suspend fun getParticleVariable(varName: String): Int {
        val response = authenticatedApi.getDevices().body().first {
            // NJD TODO - Need to get this value dynamically via telemetry from Gaggia
            it.name.equals("roboGaggia3")
        }.let { device ->
            authenticatedApi.getVariableValue(
                deviceId = device.id!!,
                varName = varName,
                productIdOrSlug = "",
            )
        }

        if (response.success) {
            return response.body().result?.toInt() ?: -1
        } else {
            throw Exception("Failed to get '$varName'")
        }
    }

    data class SettingsState(
        val referenceCupWeight: Int,
        val weightToBeanRatio: Int,
        val submissionState: SubmissionState = SubmissionState.Init,
    )

    sealed interface SubmissionState {
        data object Init : SubmissionState
        data object Loading : SubmissionState
        data object Success : SubmissionState
        data object Error : SubmissionState
    }
}