package com.example.autovault.ui

//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//
//class SecureVaultScreen {
//
//    @Composable
//    fun SecureVaultScreen() {
//        Text("Secure Vault Screen")
//    }
//}
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

@Composable
fun SecureVaultScreen() {
    var pin by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pin,
            onValueChange = { pin = it },
            label = { Text("Valet PIN") }
        )
        Button(onClick = {
            val masterKey = MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

            val sharedPreferences = EncryptedSharedPreferences.create(
                context,
                "secure_prefs",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )

            sharedPreferences.edit().putString("valet_pin", pin).apply()
        }) {
            Text("Save Securely")
        }
    }
}
