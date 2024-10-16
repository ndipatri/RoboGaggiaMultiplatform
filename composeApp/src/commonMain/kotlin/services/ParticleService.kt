package services

import com.ndipatri.kmp.openapi.particle.apis.DefaultApi
import com.ndipatri.robogaggia.BuildKonfig

class ParticleService {

    val authenticatedApi = DefaultApi().apply {
        setBearerToken(BuildKonfig.PARTICLE_ACCESS_TOKEN)
    }

    suspend fun setReferenceCupWeight(cupWeight: Int) {
        authenticatedApi.getDevices().body().filter {
            // NJD TODO - Need to get this value dynamically via telemetry from Gaggia
            it.name.equals("roboGaggia3")
        }.first().let { device ->
            val value = authenticatedApi.callFunction(
                deviceId = device.id!!,
                functionName = "setReferenceCupWeight",
                requestBody = mapOf("arg" to cupWeight.toString(), "format" to "string"),
                productIdOrSlug = "",
            ).body().returnValue
        }
    }

    suspend fun getReferenceCupWeight(): Int {
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
}