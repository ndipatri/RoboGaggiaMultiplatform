package com.ndipatri.roboaggia

import App
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.BLUETOOTH_CONNECT
import android.Manifest.permission.BLUETOOTH_SCAN
import android.Manifest.permission.RECORD_AUDIO
import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.ndipatri.robogaggia.BuildKonfig
import dev.bluefalcon.ApplicationContext
import dev.bluefalcon.BlueFalcon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : ComponentActivity() {

    val permissionAcquiredState = MutableStateFlow<Boolean>(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (needToAskForRuntimePermissions()) {
            // NJD TODO - We need some way to inform the view model
            // inside shared via state update that we've acquired
            // permission and we should start to scan.. until then
            // scanning can just keep retrying ...
            askForRuntimePermissions {
                println("*** VM: Permission acquired.")
                permissionAcquiredState.value = true
            }
        } else {
            permissionAcquiredState.value = true
        }

        setContent {
            val bluetoothPermissionAcquired by permissionAcquiredState.collectAsState()
            App(bluetoothPermissionAcquired)
        }
    }

    private fun askForRuntimePermissions(doWhenPermissionAcquired: (() -> Unit)? = null) {

        val permissions = mutableListOf<String>()

        if (!BuildKonfig.USE_SIMULATOR.toBooleanStrict() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissions.addAll(listOf(BLUETOOTH_SCAN, BLUETOOTH_CONNECT, ACCESS_FINE_LOCATION))
        }

        permissions.add(RECORD_AUDIO)

        if (permissions.isNotEmpty()) {
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { granted ->
                val allGranted = permissions.all { granted[it] == true }

                if (allGranted) {
                    doWhenPermissionAcquired?.invoke()
                } else {
                    Toast.makeText(this, "Cannot proceed without permission.", Toast.LENGTH_SHORT)
                        .show()
                }
            }.apply {
                launch(permissions.toTypedArray())
            }
        }
    }

    private fun needToAskForRuntimePermissions() =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                (
                    ActivityCompat.checkSelfPermission(this, BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                )
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(false)
}