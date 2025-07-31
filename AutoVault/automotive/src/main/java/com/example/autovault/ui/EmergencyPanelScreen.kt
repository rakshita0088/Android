package com.example.autovault.ui


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.autovault.data.car_api.dto.EmergencyAlert
import com.example.autovault.data.car_api.dto.VehicleData
import com.example.autovault.network.RetrofitClient
import com.example.autovault.util.LocationLiveTracker
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
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
//@Composable
//fun SendSOSScreen() {
//    val context = LocalContext.current
//    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
//    var isLocationSent by remember { mutableStateOf(false) }
//
//    val locationPermissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission(),
//        onResult = { granted ->
//            if (!granted) {
//                Toast.makeText(context, "Location permission is required.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    )
//
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(16.dp)) {
//
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
//                val locationRequest = LocationRequest.create().apply {
//                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//                    interval = 1000
//                    fastestInterval = 500
//                    numUpdates = 1
//                }
//
//                val locationCallback = object : LocationCallback() {
//                    override fun onLocationResult(result: LocationResult) {
//                        val location = result.lastLocation
//                        if (location != null) {
//                            val emergency = EmergencyAlert(
//                                latitude = location.latitude,
//                                longitude = location.longitude,
//                                message = "🚨 Emergency! Help needed!",
//                                contactNumber = "8446309202"
//                            )
//                            // Proceed with sending this
//                        }
//                    }
//                }
//
//                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
//
//
//                val vehicleData = VehicleData(emergencyAlert = emergency)
//
//                        RetrofitClient.api.sendSOS(vehicleData).enqueue(object : Callback<Void> {
//                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                                Toast.makeText(context, "✅ SOS sent successfully!", Toast.LENGTH_SHORT).show()
//                                isLocationSent = true
//                            }
//
//                            override fun onFailure(call: Call<Void>, t: Throwable) {
//                                Toast.makeText(context, "❌ SOS failed: ${t.message}", Toast.LENGTH_SHORT).show()
//                            }
//                        })
//                    } else {
//                        Toast.makeText(context, "❗ Location not ready. Try again.", Toast.LENGTH_SHORT).show()
//                    }
//                }.addOnFailureListener {
//                    Toast.makeText(context, "❌ Could not get location.", Toast.LENGTH_SHORT).show()
//                }
//
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("🚨 Send SOS Alert")
//        }
//
//        if (isLocationSent) {
//            Text(
//                text = "📍 Location sent successfully!",
//                modifier = Modifier.padding(top = 16.dp),
//                color = MaterialTheme.colorScheme.primary
//            )
//        }
//    }
//}
//@Composable
//fun EmergencyPanelScreen() {
//    val context = LocalContext.current
//    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
//    var isLocationSent by remember { mutableStateOf(false) }
//
//    val locationPermissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission(),
//        onResult = { granted ->
//            if (!granted) {
//                Toast.makeText(context, "Location permission is required.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    )
//
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(16.dp)) {
//
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
//                val locationRequest = LocationRequest.create().apply {
//                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//                    interval = 1000
//                    fastestInterval = 500
//                    numUpdates = 1
//                }
//
//                val locationCallback = object : LocationCallback() {
//                    override fun onLocationResult(result: LocationResult) {
//                        val location = result.lastLocation
//                        if (location != null && location.latitude != 0.0 && location.longitude != 0.0) {
//                            val emergency = EmergencyAlert(
//                                latitude = location.latitude,
//                                longitude = location.longitude,
//                                message = "🚨 Emergency! Help needed!",
//                                contactNumber = "8446309202"
//                            )
//
//                            val vehicleData = VehicleData(emergencyAlert = emergency)
//
//                            RetrofitClient.api.sendSOS(vehicleData).enqueue(object : Callback<Void> {
//                                override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                                    Toast.makeText(context, "✅ SOS sent successfully!", Toast.LENGTH_SHORT).show()
//                                    isLocationSent = true
//                                }
//
//                                override fun onFailure(call: Call<Void>, t: Throwable) {
//                                    Toast.makeText(context, "❌ SOS failed: ${t.message}", Toast.LENGTH_SHORT).show()
//                                }
//                            })
//                        } else {
//                            Toast.makeText(context, "❗ Location not ready. Try again.", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//
//                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("🚨 Send SOS Alert")
//        }
//
//        if (isLocationSent) {
//            Text(
//                text = "📍 Location sent successfully!",
//                modifier = Modifier.padding(top = 16.dp),
//                color = MaterialTheme.colorScheme.primary
//            )
//        }
//    }
//}
