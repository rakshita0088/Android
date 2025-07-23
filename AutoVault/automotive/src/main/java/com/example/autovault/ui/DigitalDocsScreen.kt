package com.example.autovault.ui




import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

@Composable
fun DigitalDocsScreen() {
    val context = LocalContext.current
    var selectedFileName by remember { mutableStateOf<String?>(null) }
    var storedFileUri by remember { mutableStateOf<String?>(loadFileUri(context)) }

    // Launcher to pick file
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            uri?.let {
                context.contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                storeFileUri(context, it.toString())
                storedFileUri = it.toString()
                selectedFileName = getFileNameFromUri(context, it)
            }
        }
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Upload or View Car Documents", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Allow any file type like PDF, JPEG, etc.
            filePickerLauncher.launch(arrayOf("*/*"))
        }) {
            Text("Upload RC Document")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            storedFileUri?.let { uriString ->
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(uriString)
                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                }
                context.startActivity(intent)
            }
        }, enabled = storedFileUri != null) {
            Text("View Insurance Document")
        }

        Spacer(modifier = Modifier.height(8.dp))

        selectedFileName?.let {
            Text("Selected: $it", style = MaterialTheme.typography.bodyMedium)
        }

        if (storedFileUri == null) {
            Text("No document uploaded yet", style = MaterialTheme.typography.bodySmall)
        }
    }
}
private fun storeFileUri(context: Context, uri: String) {
    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    val sharedPrefs = EncryptedSharedPreferences.create(
        context,
        "secure_docs_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    sharedPrefs.edit().putString("document_uri", uri).apply()
}

private fun loadFileUri(context: Context): String? {
    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    val sharedPrefs = EncryptedSharedPreferences.create(
        context,
        "secure_docs_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    return sharedPrefs.getString("document_uri", null)
}

private fun getFileNameFromUri(context: Context, uri: Uri): String? {
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    return cursor?.use {
        val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        it.moveToFirst()
        it.getString(nameIndex)
    }
}
