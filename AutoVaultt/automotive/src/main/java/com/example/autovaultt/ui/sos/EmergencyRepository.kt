package com.example.autovaultt.ui.sos




import android.Manifest
import android.content.Context
import android.util.Log
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.FirebaseDatabase

class EmergencyRepository {

    private val database = FirebaseDatabase.getInstance().getReference("emergencySOS")

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun getCurrentLocation(context: Context, callback: (Double, Double) -> Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    callback(location.latitude, location.longitude)
                } else {
                    Log.e("SOS", "Location is null")
                    callback(0.0, 0.0) // fallback
                }
            }
            .addOnFailureListener {
                Log.e("SOS", "Location failed: ${it.message}")
            }
    }

    fun sendToFirebase(lat: Double, lon: Double, message: String, result: (String) -> Unit) {
        val sosData = mapOf(
            "latitude" to lat,
            "longitude" to lon,
            "message" to message,
            "timestamp" to System.currentTimeMillis()
        )

        database.setValue(sosData)
            .addOnSuccessListener {
                result("✅ SOS Sent Successfully!")
            }
            .addOnFailureListener {
                result("❌ Failed to send SOS: ${it.message}")
            }
    }
}
