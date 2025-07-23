package com.example.autovault

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.autovault.ui.DashboardScreen
import com.example.autovault.ui.DigitalDocsScreen
import com.example.autovault.ui.QuickFixHelpScreen
import com.example.autovault.ui.SecureVaultScreen
import com.example.autovault.ui.ServiceRemindersScreen
import com.example.autovault.ui.SetupScreen



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoVaultApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("AutoVault Dashboard") })
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("dashboard") { DashboardScreen(navController) }
            composable("secureVault") { SecureVaultScreen() }
            composable("digitalDocs") { DigitalDocsScreen() }
            composable("quickFix") { QuickFixHelpScreen(navController) }
            composable("serviceReminder") {
                val context = androidx.compose.ui.platform.LocalContext.current
                ServiceRemindersScreen(context)
            }
            composable("setup") { SetupScreen() }
        }
    }
}
