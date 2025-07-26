package com.example.autovault.ui



//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.provider.OpenableColumns
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import java.io.File
//import java.io.FileOutputStream
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DigitalDocsScreen() {
//    val context = LocalContext.current
//    var savedFiles by remember { mutableStateOf(loadSavedFiles(context)) }
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        uri?.let {
//            val fileName = getFileNameFromUri(context, it)
//            if (fileName != null) {
//                saveFileToInternalStorage(context, it, fileName)
//                savedFiles = loadSavedFiles(context) // refresh UI
//            }
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("Digital Docs") })
//        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = { launcher.launch("*/*") }) {
//                Text("+")
//            }
//        }
//    ) { padding ->
//        LazyColumn(
//            modifier = Modifier
//                .padding(padding)
//                .padding(16.dp)
//        ) {
//            items(savedFiles) { file ->
//                Text(
//                    text = file.name,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable { openFile(context, file) }
//                        .padding(12.dp)
//                )
//            }
//        }
//    }
//}
//
//// 📂 Save the selected file into internal storage
//fun saveFileToInternalStorage(context: Context, uri: Uri, fileName: String) {
//    val inputStream = context.contentResolver.openInputStream(uri)
//    val outputFile = File(context.filesDir, fileName)
//    val outputStream = FileOutputStream(outputFile)
//    inputStream?.copyTo(outputStream)
//    inputStream?.close()
//    outputStream.close()
//}
//
//// 📂 Load saved files from internal storage
//fun loadSavedFiles(context: Context): List<File> {
//    return context.filesDir.listFiles()?.toList() ?: emptyList()
//}
//
//// 📄 Get filename from URI
//fun getFileNameFromUri(context: Context, uri: Uri): String? {
//    val cursor = context.contentResolver.query(uri, null, null, null, null)
//    cursor?.use {
//        val nameIndex = it.getColumnIndexOpenableColumnsDisplayName()
//        if (it.moveToFirst() && nameIndex != -1) {
//            return it.getString(nameIndex)
//        }
//    }
//    return uri.lastPathSegment // fallback
//}
//
//// 🔎 Extension to get column index for file display name
//fun android.database.Cursor.getColumnIndexOpenableColumnsDisplayName(): Int {
//    return getColumnIndex(OpenableColumns.DISPLAY_NAME)
//}
//
//// 📤 Open saved file using external app
//fun openFile(context: Context, file: File) {
//    val uri = androidx.core.content.FileProvider.getUriForFile(
//        context,
//        "${context.packageName}.provider", // defined in AndroidManifest
//        file
//    )
//
//    val intent = Intent(Intent.ACTION_VIEW).apply {
//        setDataAndType(uri, context.contentResolver.getType(uri))
//        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//    }
//
//    context.startActivity(intent)
//}





import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.Toast
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
//@Composable
//fun DigitalDocsScreen() {
//    val context = LocalContext.current
//    var pickedUri by remember { mutableStateOf<Uri?>(null) }
//
//    // File picker launcher
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        pickedUri = uri
//        uri?.let {
//            Toast.makeText(context, "File Selected: $uri", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        Text("Digital Document Vault", style = MaterialTheme.typography.headlineSmall)
//
//        // Pick PDF Button
//        Button(onClick = {
//            launcher.launch("application/pdf") // open file picker for PDF only
//        }) {
//            Text("Upload PDF Document")
//        }
//
//        // View selected PDF
//        pickedUri?.let { uri ->
//            Button(onClick = {
//                val viewIntent = Intent(Intent.ACTION_VIEW).apply {
//                    setDataAndType(uri, "application/pdf")
//                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK
//                }
//
//                try {
//                    context.startActivity(viewIntent)
//                } catch (e: Exception) {
//                    Toast.makeText(context, "No app found to open PDF", Toast.LENGTH_SHORT).show()
//                }
//            }) {
//                Text("View Uploaded Document")
//            }
//        }
//    }
//}
