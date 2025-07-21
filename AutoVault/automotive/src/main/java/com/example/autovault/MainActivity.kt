package com.example.autovault

//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import com.example.autovault.theme.AutoVaultTheme
//import com.example.autovault.ui.EmergencyPanelScreen
//import com.google.firebase.FirebaseApp
//
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        FirebaseApp.initializeApp(this)
//        setContent {
//            AutoVaultTheme {
//                AutoVaultApp().AutoVaultApp()
//                EmergencyPanelScreen()
//            }
//        }
//    }
//}


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.autovault.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoVaultApp()
        }
    }
}





