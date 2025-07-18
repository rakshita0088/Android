package com.example.autovault.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
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
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun EmergencyPanelScreen() {
    val context = LocalContext.current

    // ✅ Get location client (for GPS)
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    // ✅ Get Firestore instance (correct way)
    val firestore = FirebaseFirestore.getInstance()

    // ✅ Location permission launcher
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Toast.makeText(context, "Permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    // ✅ UI Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Emergency Panel", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))

        // ✅ SOS Button
        Button(
            onClick = {
                // Check location permission
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                } else {
                    // ✅ Get last known location
                    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                        location?.let {
                            val lat = it.latitude
                            val lon = it.longitude
                            val mapsLink = "https://maps.google.com/?q=$lat,$lon"
                            val message = "🚨 EMERGENCY! Please help me. Location: $mapsLink"
                            val phoneNumber = "+918446309202" // <-- Replace with real number

                            // ✅ Build Firestore document
                            val alert = hashMapOf(
                                "message" to message,
                                "phoneNumber" to phoneNumber
                            )

                            // ✅ Add to Firestore
                            firestore.collection("alerts")
                                .add(alert)
                                .addOnSuccessListener {
                                    Toast.makeText(context, "SOS Sent!", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Failed to send SOS", Toast.LENGTH_SHORT).show()
                                }
                        } ?: Toast.makeText(context, "No location available", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SEND SOS")
        }
    }
}





