package com.example.autovaultlistener

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.*
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    private val permissions = arrayOf(
        Manifest.permission.SEND_SMS,
        Manifest.permission.READ_SMS,
        Manifest.permission.RECEIVE_SMS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            MaterialTheme {
                SOSListenerScreen()
            }
        }

        // Request SMS permissions
        permissions.forEach {
            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, 101)
            }
        }
    }

    private fun sendSMS(phone: String, message: String) {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phone, null, message, null, null)
        Toast.makeText(this, "SMS sent to $phone", Toast.LENGTH_SHORT).show()
    }

    @Composable
    fun SOSListenerScreen() {
        val context = this
        var status by remember { mutableStateOf("Waiting for SOS messages...") }

        LaunchedEffect(Unit) {
            val firestore = FirebaseFirestore.getInstance()

            firestore.collection("sos_requests")
                .addSnapshotListener { snapshots, error ->
                    if (error != null) {
                        status = "Firestore error: ${error.message}"
                        return@addSnapshotListener
                    }

                    for (docChange in snapshots!!.documentChanges) {
                        if (docChange.type == DocumentChange.Type.ADDED) {
                            val message = docChange.document.getString("message") ?: "No message"
                            val phone = docChange.document.getString("receiverPhone") ?: ""
                            val location = docChange.document.getString("location") ?: ""

                            val fullMessage = "$message\nLocation: $location"

                            sendSMS(phone, fullMessage)
                            status = "SMS sent to $phone"
                        }
                    }
                }
        }

        Surface {
            Text(text = status, modifier = Modifier.padding(16.dp))
        }
    }
}
