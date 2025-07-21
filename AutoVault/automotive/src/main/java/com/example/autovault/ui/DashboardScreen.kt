package com.example.autovault.ui

//import android.R
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.autovault.R



//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.material3.Text
//import androidx.compose.material3.Button
//import androidx.compose.ui.Alignment
//import androidx.navigation.NavController
//import androidx.navigation.NavHostController
//
//
//@Composable
//fun DashboardScreen(navController: NavController) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceEvenly,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("AutoVault", style = MaterialTheme.typography.headlineMedium)
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // First Row
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
//            FeatureButton("SecureVault") { navController.navigate("secureVault") }
//            FeatureButton("DigitalDoc") { navController.navigate("digitalDocs") }
//        }
//
//        // Second Row
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
//            FeatureButton("QuickFix") { navController.navigate("quickFix") }
//            FeatureButton("ServiceRem") { navController.navigate("serviceRem") }
//        }
//
//        // Emergency SOS Button (large)
//        Button(
//            onClick = { navController.navigate("emergency") },
//                 modifier = Modifier
//                .fillMaxWidth()
//                .height(60.dp)
//                .padding(top = 24.dp)
//        ) {
//            Text("Emergency SOS")
//        }
//    }
//}
//
//@Composable
//fun FeatureButton(title: String, onClick: () -> Unit) {
//    Button(
//        onClick = onClick,
//        modifier = Modifier
//            .width(140.dp)
//            .height(80.dp)
//    ) {
//        Text(title)
//    }
//}
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//
//@Composable
//fun DashboardScreen(navController: NavController) {
//    Column(modifier = Modifier.padding(16.dp)) {
//        FeatureCard("Secure Vault") { navController.navigate("secureVault") }
//        FeatureCard("Digital Documents") { navController.navigate("digitalDocs") }
//        FeatureCard("Quick Fix Help") { navController.navigate("quickFix") }
//        FeatureCard("Service Reminders") { navController.navigate("serviceReminder") }
//        FeatureCard("Setup Screen") { navController.navigate("setup") }
//    }
//}
//
//@Composable
//fun FeatureCard(title: String, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp)
//            .clickable { onClick() },
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = title, style = MaterialTheme.typography.titleMedium)
//        }
//    }
//}



//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//
//@Composable
//fun DashboardScreen(navController: NavController) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        // Title
//        Text(
//            text = "AutoVault",
//            fontSize = 28.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Row 1: Secure Vault + Digital Docs
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            DashboardCard("Secure Vault") {
//                navController.navigate("secureVault")
//            }
//            DashboardCard("Digital Docs") {
//                navController.navigate("digitalDocs")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Row 2: Quick Fix Help + Service Reminder
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            DashboardCard("Quick Fix Help") {
//                navController.navigate("quickFixHelp")
//            }
//            DashboardCard("Service Reminder") {
//                navController.navigate("serviceReminder")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Row 3: Emergency SOS (full width)
//        DashboardCard(
//            title = "Emergency SOS",
//            modifier = Modifier.fillMaxWidth(),
//            onClick = {
//                navController.navigate("emergencySOS")
//            }
//        )
//    }
//}
//
//@Composable
//fun DashboardCard(
//    title: String,
//    modifier: Modifier = Modifier
//        .width(160.dp)
//        .height(100.dp),
//    onClick: () -> Unit
//) {
//    Surface(
//        modifier = modifier
//            .clickable { onClick() }
//            .background(Color(0xFFE0F7FA), shape = RoundedCornerShape(16.dp)),
//        shape = RoundedCornerShape(16.dp),
//        tonalElevation = 4.dp,
//        shadowElevation = 4.dp,
//        color = Color(0xFFE0F7FA)
//    ) {
//        Box(contentAlignment = Alignment.Center) {
//            Text(
//                text = title,
//                fontWeight = FontWeight.Medium,
//                fontSize = 16.sp
//            )
//        }
//    }
//}


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.autovault.R

@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        // App Logo
//        Image(
//            painter = painterResource(id = R.drawable.logoav),
//            contentDescription = "App Logo",
//            modifier = Modifier
//                .size(100.dp)
//                .padding(8.dp),
//            contentScale = ContentScale.Fit
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))

        // First Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FeatureCard("Secure Vault", R.drawable.securevault) {
                navController.navigate("secureVault")
            }
            FeatureCard("Digital Docs", R.drawable.digitaldoc) {
                navController.navigate("digitalDocs")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Second Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FeatureCard("Quick Fix Help", R.drawable.quickfixhelp) {
                navController.navigate("quickFixHelp")
            }
            FeatureCard("Service Reminder", R.drawable.servicereminder) {
                navController.navigate("serviceReminder")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Emergency SOS (Center-Aligned Single Card)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            FeatureCard("Emergency SOS", R.drawable.emergencypanel) {
                navController.navigate("emergencySOS")
            }
        }
    }
}

@Composable
fun FeatureCard(title: String, iconResId: Int, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .size(140.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
}
