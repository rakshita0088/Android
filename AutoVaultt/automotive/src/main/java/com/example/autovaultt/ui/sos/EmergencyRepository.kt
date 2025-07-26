package com.example.autovaultt.ui.sos

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.firebase.database.FirebaseDatabase

class EmergencyRepository {

    private val database = FirebaseDatabase.getInstance()
    private val sosRef = database.getReference("sos_data")

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(context: Context, callback: (Double, Double) -> Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permissions are missing
            callback(0.0, 0.0)
            return
        }

        val locationRequest = LocationRequest.create().apply {
            interval = 1000
            fastestInterval = 500
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            numUpdates = 1
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val location: Location = locationResult.lastLocation!!
                    callback(location.latitude, location.longitude)
                }
            },
            Looper.getMainLooper()
        )
    }

    fun sendToFirebase(lat: Double, lon: Double, message: String, onComplete: (String) -> Unit) {
        val sosMap = mapOf(
            "latitude" to lat,
            "longitude" to lon,
            "message" to message,
            "timestamp" to System.currentTimeMillis()
        )

        sosRef.setValue(sosMap)
            .addOnSuccessListener {
                onComplete("SOS Sent!")
            }
            .addOnFailureListener {
                onComplete("Failed to send SOS")
            }
    }
}
