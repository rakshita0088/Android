package com.example.autovault

import androidx.compose.runtime.Composable
import com.example.autovault.theme.AutoVaultTheme
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.autovault.ui.*

class AutoVaultApp {

    @Composable
    fun AutoVaultApp() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "dashboard") {
            composable("dashboard") { DashboardScreen(navController) }
            composable("secureVault") { SecureVaultScreen() }
            composable("digitalDocs") { DigitalDocsScreen() }
            composable("quickFix") { QuickFixHelpScreen() }
            composable("serviceReminder") { ServiceReminderScreen() }
            composable("emergency") {
                val context = androidx.compose.ui.platform.LocalContext.current
                EmergencyPanelScreen(context)  // ✅ Now context is passed correctly
            }
        }
    }
}
