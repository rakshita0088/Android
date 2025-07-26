package com.example.autovaultt.ui.sos



import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel

class EmergencyViewModel : ViewModel() {
    private val repository = EmergencyRepository()

    fun sendEmergencySOS(context: Context, onResult: (String) -> Unit) {
        repository.getCurrentLocation(context) { lat, lon ->
            val message =
                "🚨 AutoVault SOS Alert\nI am in danger. Please help me.\nLocation: https://maps.google.com/?q=$lat,$lon"

            Log.d("SOS", "Sending message: $message") // ✅ Add this
            Toast.makeText(context, "Sending SOS...", Toast.LENGTH_SHORT).show() // ✅ Add this

            repository.sendToFirebase(lat, lon, message) { result ->
                Log.d("SOS", "Firebase result: $result") // ✅ Log Firebase response
                onResult(result)
            }
        }
    }}



