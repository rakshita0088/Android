package com.example.autovault.ui
//
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//
//class QuickFixHelpScreen {
//    @Composable
//    fun QuickFixHelpScreen () {
//        Text("Quick Fix Help Screen")
//    }
//}
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuickFixHelpScreen() {
    Column(Modifier.padding(16.dp)) {
        Text("Troubleshooting Steps")
        Text("Battery: Check terminals, charge level")
        Text("Tyre: Inspect pressure, look for puncture")
        Text("Engine: Look for warning light or leaks")
    }
}
