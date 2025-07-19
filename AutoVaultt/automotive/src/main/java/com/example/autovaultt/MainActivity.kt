package com.example.autovaultt

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import com.example.autovaultt.ui.sos.EmergencyScreen


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmergencyScreen() // 👉 shows the SOS button
        }
    }

    private fun setContent(function: () -> Unit) {}
}
