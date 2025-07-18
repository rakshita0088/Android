package com.example.autovault.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("AutoVault", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        // First Row
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            FeatureButton("SecureVault") { navController.navigate("secureVault") }
            FeatureButton("DigitalDoc") { navController.navigate("digitalDocs") }
        }

        // Second Row
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            FeatureButton("QuickFix") { navController.navigate("quickFix") }
            FeatureButton("ServiceRem") { navController.navigate("serviceRem") }
        }

        // Emergency SOS Button (large)
        Button(
            onClick = { navController.navigate("emergency") },
                 modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 24.dp)
        ) {
            Text("Emergency SOS")
        }
    }
}

@Composable
fun FeatureButton(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(140.dp)
            .height(80.dp)
    ) {
        Text(title)
    }
}
