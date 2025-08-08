package com.example.car.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.car.data.car_api.SpeedViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SpeedMonitorScreen(viewModel: SpeedViewModel = viewModel()) {
    val currentSpeed = viewModel.speed.collectAsState(initial = 0).value
    val speedLimit = viewModel.speedLimit.collectAsState(initial = 0).value


    LaunchedEffect(Unit) {
        viewModel.collectData() // Start collecting car data once
    }
    val vehicleData = viewModel.vehicleData.value

    Column(Modifier.padding(16.dp)) {
        Text("Current Speed (GPS): $currentSpeed km/h", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = speedLimit.toString(),
            onValueChange = { viewModel.setSpeedLimit(it.toIntOrNull() ?: 0) },
            label = { Text("Set Max Speed (km/h)") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (speedLimit > 0 && currentSpeed > speedLimit) {
            Text("⚠️ Speed Limit Exceeded!", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Divider()

        Spacer(modifier = Modifier.height(16.dp))
        Text("🚘 Vehicle Info", style = MaterialTheme.typography.titleLarge)

        if (vehicleData != null) {
            Text("Ignition Status: ${vehicleData.ignitionStatus.status}")
            Text("Ignition Value: ${vehicleData.ignitionStatus.value}")
            Text("Speed Sensor Value: ${vehicleData.speed.value} ${vehicleData.speed.unit}")
        } else {
            Text("Fetching vehicle data...")
        }
    }
}