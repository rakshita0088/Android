package com.example.autovaultt.ui.sos


import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.autovaultt.ui.sos.EmergencyRepository

class EmergencyViewModel : ViewModel() {
    private val repository = EmergencyRepository()

    fun sendEmergencySOS(context: Context, onResult: (String) -> Unit) {
        repository.getCurrentLocation(context) { lat, lon ->
            val message = "🚨 AutoVault SOS Alert\nI am in danger. Please help me.\nLocation: https://maps.google.com/?q=$lat,$lon"
            repository.sendToFirebase(lat, lon, message, onResult)
        }
    }
}

