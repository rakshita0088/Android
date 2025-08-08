package com.example.car

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.car.ui.AppContextProvider
import com.example.car.ui.SpeedMonitorScreen
import com.example.car.ui.theme.Theme


class MainActivity : ComponentActivity() {
    private val REQUEST_LOCATION = 1001

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestLocationPermission()
        AppContextProvider.init(this)

        setContent {
            Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    SpeedMonitorScreen()
                }
            }
        }
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION)
        }
    }
}