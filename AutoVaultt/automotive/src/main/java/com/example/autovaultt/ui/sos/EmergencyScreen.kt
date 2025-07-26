package com.example.autovaultt.ui.sos

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable

fun EmergencyScreen(viewModel: EmergencyViewModel = viewModel()) {
    val context = LocalContext.current // ✅ Moved outside
    var result by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            viewModel.sendEmergencySOS(context = context) { // ✅ use the stored context
                result = it
            }
        }) {
            Text("Send SOS")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Status: $result")
    }
}
