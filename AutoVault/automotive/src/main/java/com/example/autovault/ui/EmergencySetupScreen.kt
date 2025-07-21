package com.example.autovault.ui

//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//
//class EmergencySetupScreen {
//    @Composable
//    fun EmergencySetupScreen() {
//        Text("Emergency Setup Screen")
//    }
//}
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SetupScreen() {
    var contact by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("sos_prefs", Context.MODE_PRIVATE)

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = contact,
            onValueChange = { contact = it },
            label = { Text("Emergency Contact") }
        )
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Custom SOS Message") }
        )
        Button(onClick = {
            sharedPreferences.edit().apply {
                putString("contact", contact)
                putString("message", message)
                apply()
            }
        }) {
            Text("Save Settings")
        }
    }
}
