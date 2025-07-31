package com.example.autovault.ui


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.autovault.data.car_api.dto.EmergencyAlert
import com.example.autovault.data.car_api.dto.VehicleData
import com.example.autovault.network.RetrofitClient
import com.example.autovault.util.LocationLiveTracker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//@Composable
//fun EmergencyPanelScreen(context: Context) {
//    var isLocationSent by remember { mutableStateOf(false) }
//    var permissionGranted by remember { mutableStateOf(false) }
//
//    val locationPermissionLauncher = rememberLauncherForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) { granted ->
//        permissionGranted = granted
//    }
//
//    var locationTracker: LocationLiveTracker? by remember { mutableStateOf(null) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center
//    ) {
//        Button(
//            onClick = {
//                if (ActivityCompat.checkSelfPermission(
//                        context,
//                        Manifest.permission.ACCESS_FINE_LOCATION
//                    ) != PackageManager.PERMISSION_GRANTED
//                ) {
//                    locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//                    return@Button
//                }
//
//                locationTracker = LocationLiveTracker(context) { location ->
//                    val latitude = location.latitude
//                    val longitude = location.longitude
//
//
//                    val emergency = EmergencyAlert(
//                        latitude = latitude,
//                        longitude = longitude,
//                        message = "🚨 Emergency! Help needed!",
//                        contactNumbers = "9036417577",
//                        isButtonClicked = "Yes"
//                    )
//
//                    val vehicleData = VehicleData(emergencyAlert = emergency)
//
//                    RetrofitClient.api.sendSOS(vehicleData).enqueue(object : Callback<Void> {
//                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                            println("✅ SOS sent successfully!")
//                            isLocationSent = true
//                        }
//
//                        override fun onFailure(call: Call<Void>, t: Throwable) {
//                            println("❌ Failed to send SOS: ${t.message}")
//                        }
//                    })
//
//                    locationTracker?.stopTracking()
//                }
//
//                locationTracker?.startTracking()
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("🚨 Send SOS Alert")
//        }
//
//        if (isLocationSent) {
//            Text("✅ Location sent successfully!", modifier = Modifier.padding(top = 16.dp))
//        }
//    }
//}
@Composable
fun EmergencyPanelScreen(context: Context) {
    var isLocationSent by remember { mutableStateOf(false) }
    var permissionGranted by remember { mutableStateOf(false) }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        permissionGranted = granted
    }

    var locationTracker: LocationLiveTracker? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    return@Button
                }

                locationTracker = LocationLiveTracker(context) { location ->
                    val latitude = location.latitude
                    val longitude = location.longitude

                    // 🔁 New EmergencyAlert wrapped in VehicleData
                    val emergency = EmergencyAlert(
                        latitude = latitude,
                        longitude = longitude,
                        message = "🚨 Emergency! Help needed!",
                        contactNumber = "8446309202"
                    )

                    val vehicleData = VehicleData(emergencyAlert = emergency)

                    RetrofitClient.api.sendSOS(vehicleData).enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            println("✅ SOS sent successfully!")
                            isLocationSent = true
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            println("❌ Failed to send SOS: ${t.message}")
                        }
                    })

                    locationTracker?.stopTracking()
                }

                locationTracker?.startTracking()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🚨 Send SOS Alert")
        }

        if (isLocationSent) {
            Text("✅ Location sent successfully!", modifier = Modifier.padding(top = 16.dp))
        }
    }
}