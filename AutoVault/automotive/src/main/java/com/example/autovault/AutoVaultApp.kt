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

//
//import androidx.compose.runtime.Composable
//import com.example.autovault.theme.AutoVaultTheme
//import androidx.compose.material3.Surface
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.autovault.ui.*
//
//class AutoVaultApp {
//
//    @Composable
//    fun AutoVaultApp() {
//        val navController = rememberNavController()
//
//        NavHost(navController = navController, startDestination = "dashboard") {
//            composable("dashboard") { DashboardScreen(navController) }
//            composable("secureVault") { SecureVaultScreen() }
//            composable("digitalDocs") { DigitalDocsScreen() }
//            composable("quickFix") { QuickFixHelpScreen() }
//            composable("serviceReminder") { ServiceReminderScreen() }
//            composable("emergency") {
//                val context = androidx.compose.ui.platform.LocalContext.current
//                EmergencyPanelScreen(context)  // ✅ Now context is passed correctly
//            }
//        }
//    }
//}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AutoVaultApp() {
//    val navController = rememberNavController()
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("AutoVault Dashboard") })
//        }
//    ) { paddingValues ->
//        NavHost(
//            navController = navController,
//            startDestination = "dashboard",
//            modifier = Modifier.padding(paddingValues)
//        ) {
//            composable("dashboard") { DashboardScreen(navController) }
//            composable("secureVault") { SecureVaultScreen() }
//            composable("digitalDocs") { DigitalDocsScreen() }
//            composable("quickFix") { QuickFixHelpScreen() }
//            composable("serviceReminder") {
//                val context = androidx.compose.ui.platform.LocalContext.current
//                ServiceRemindersScreen(context)
//            }
//
//            composable("setup") { SetupScreen() }
//        }
//    }
//}
// File: AutoVaultApp.kt
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.*
//import com.example.autovault.ui.*

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
            composable("quickFix") { QuickFixHelpScreen() }
            composable("serviceReminder") {
                val context = androidx.compose.ui.platform.LocalContext.current
                ServiceRemindersScreen(context)
            }
            composable("setup") { SetupScreen() }
        }
    }
}
