package com.example.car.ui
//
//import android.os.Build
//import android.util.Log
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.car.data.car_api.SpeedViewModel
//import com.example.car.data.car_api.dto.Rental
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//
//@RequiresApi(Build.VERSION_CODES.P)
//@Composable
//fun SpeedMonitorScreen(viewModel: SpeedViewModel = viewModel()) {
//    val currentSpeed = viewModel.speed.collectAsState(initial = 0).value
//    val speedLimit = viewModel.speedLimit.collectAsState(initial = 0).value
//
//
//    LaunchedEffect(Unit) {
//        viewModel.collectData() // Start collecting car data once
//    }
//    val vehicleData = viewModel.vehicleData.value
//
//    Column(Modifier.padding(16.dp)) {
//        Text("Current Speed (GPS): $currentSpeed km/h", style = MaterialTheme.typography.headlineSmall)
//        Spacer(modifier = Modifier.height(12.dp))
//
//        OutlinedTextField(
//            value = speedLimit.toString(),
//            onValueChange = { viewModel.setSpeedLimit(it.toIntOrNull() ?: 0) },
//            label = { Text("Set Max Speed (km/h)") },
//            singleLine = true
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        if (speedLimit > 0 && currentSpeed > speedLimit) {
//            Text("⚠️ Speed Limit Exceeded!", color = MaterialTheme.colorScheme.error)
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Divider()
//
//        Spacer(modifier = Modifier.height(16.dp))
//        Text("🚘 Vehicle Info", style = MaterialTheme.typography.titleLarge)
//
//        if (vehicleData != null) {
//            Text("Ignition Status: ${vehicleData.ignitionStatus.status}")
//            Text("Ignition Value: ${vehicleData.ignitionStatus.value}")
//            Text("Speed Sensor Value: ${vehicleData.speed.value} ${vehicleData.speed.unit}")
//
//            if(vehicleData.ignitionStatus.value == 2){
//                pushMockRentalData()
//            }
//        } else {
//            Text("Fetching vehicle data...")
//        }
//
//
//    }
//}
//
// fun pushMockRentalData() {
//
//      lateinit var database: FirebaseDatabase
//      lateinit var rentalsRef: DatabaseReference
//    Log.d("RTDB_PUSH", "Attempting to push mock data...")
//
//    // Create some mock Rental data instances
//    val rental1 = Rental(
//        customerId = "cust_john_doe",
//        vehicleId = "car_sedan_001",
//        maxSpeedLimitKmph = 100,
//        notificationChannel = "firebase_fcm",
//        rentalStatus = "active"
//    )
//
//    val rental2 = Rental(
//        customerId = "cust_jane_smith",
//        vehicleId = "car_suv_002",
//        maxSpeedLimitKmph = 90,
//        notificationChannel = "aws_sns",
//        rentalStatus = "scheduled"
//    )
//
//    val rental3 = Rental(
//        customerId = "cust_john_doe", // Same customer, but a different rental
//        vehicleId = "car_truck_003",
//        maxSpeedLimitKmph = 110,
//        notificationChannel = "firebase_fcm",
//        rentalStatus = "active"
//    )
//
//     database = FirebaseDatabase.getInstance("https://carr-2336b-default-rtdb.firebaseio.com")
//     rentalsRef = database.getReference("rentals") // Points to the "rentals" node in your database
//
//    // Example 1: Push `rental1` with a specific, identifiable key ("rental_ABC_123")
//    rentalsRef.child("rental_ABC_123").setValue(rental1)
//        .addOnSuccessListener {
//            Log.d("RTDB_PUSH", "rental_ABC_123 pushed successfully!")
//        }
//        .addOnFailureListener { e ->
//            Log.e("RTDB_PUSH", "Failed to push rental_ABC_123: ${e.message}", e)
//        }
//
//    // Example 2: Push `rental2` with another specific key ("rental_XYZ_456")
//    rentalsRef.child("rental_XYZ_456").setValue(rental2)
//        .addOnSuccessListener {
//            Log.d("RTDB_PUSH", "rental_XYZ_456 pushed successfully!")
//        }
//        .addOnFailureListener { e ->
//            Log.e("RTDB_PUSH", "Failed to push rental_XYZ_456: ${e.message}", e)
//        }
//
//    // Example 3: Push `rental3` and let Firebase generate a unique, time-based key
//    // This is often preferred when you don't have a natural unique ID for each item.
//    rentalsRef.push().setValue(rental3)
//        .addOnSuccessListener {
//            Log.d("RTDB_PUSH", "rental_DEF_789 (generated key) pushed successfully!")
//        }
//        .addOnFailureListener { e ->
//            Log.e("RTDB_PUSH", "Failed to push rental_DEF_789 (generated key): ${e.message}", e)
//        }
//}
//
//package com.example.car.ui

//import android.os.Build
//import android.util.Log
//import android.widget.Toast
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.car.data.car_api.SpeedViewModel
//import com.example.car.data.car_api.dto.Rental
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//
//@RequiresApi(Build.VERSION_CODES.P)
//@Composable
//fun SpeedMonitorScreen(viewModel: SpeedViewModel = viewModel()) {
//    val context = LocalContext.current
//    val speedLimit = viewModel.speedLimit.collectAsState().value
//    val vehicleData = viewModel.vehicleData.value
//
//    LaunchedEffect(Unit) { viewModel.collectData() }
//
//    Column(Modifier.padding(16.dp)) {
//        OutlinedTextField(
//            value = speedLimit.toString(),
//            onValueChange = { viewModel.setSpeedLimit(it.toIntOrNull() ?: 0) },
//            label = { Text("Set Max Speed (km/h)") },
//            singleLine = true
//        )
//        Spacer(Modifier.height(16.dp))
//
//        if (vehicleData != null) {
//            Text("Ignition Status: ${vehicleData.ignitionStatus.value}")
//            Text("Speed: ${vehicleData.speed.value} ${vehicleData.speed.unit}")
//
//            // Push mock data if ignition = 2
//            if (vehicleData.ignitionStatus.value == 2) {
//                pushMockRentalData()
//            }
//
//            // Show warning if ignition = 4 or 5 and speed > limit
//            if ((vehicleData.ignitionStatus.value == 4 || vehicleData.ignitionStatus.value == 5)
//                && vehicleData.speed.value > speedLimit
//            ) {
//                Toast.makeText(context, "⚠️ Slow down! Speed Limit Exceeded", Toast.LENGTH_SHORT).show()
//            }
//        } else {
//            Text("Fetching vehicle data...")
//        }
//    }
//}
//
////fun pushMockRentalData() {
////    val database = FirebaseDatabase.getInstance("https://carr-2336b-default-rtdb.firebaseio.com")
////    val rentalsRef = database.getReference("rentals")
////
////    val rental1 = Rental("cust_john_doe", "car_sedan_001", 100, "firebase_fcm", "active")
////    rentalsRef.child("rental_ABC_123").setValue(rental1)
////}
//fun pushMockRentalData() {
//
//      lateinit var database: FirebaseDatabase
//      lateinit var rentalsRef: DatabaseReference
//    Log.d("RTDB_PUSH", "Attempting to push mock data...")
//
//    // Create some mock Rental data instances
//    val rental1 = Rental(
//        customerId = "cust_john_doe",
//        vehicleId = "car_sedan_001",
//        maxSpeedLimitKmph = 100,
//        notificationChannel = "firebase_fcm",
//        rentalStatus = "active"
//    )
//
//    val rental2 = Rental(
//        customerId = "cust_jane_smith",
//        vehicleId = "car_suv_002",
//        maxSpeedLimitKmph = 90,
//        notificationChannel = "aws_sns",
//        rentalStatus = "scheduled"
//    )
//
//    val rental3 = Rental(
//        customerId = "cust_john_doe", // Same customer, but a different rental
//        vehicleId = "car_truck_003",
//        maxSpeedLimitKmph = 110,
//        notificationChannel = "firebase_fcm",
//        rentalStatus = "active"
//    )
//
//     database = FirebaseDatabase.getInstance("https://carr-2336b-default-rtdb.firebaseio.com")
//     rentalsRef = database.getReference("rentals") // Points to the "rentals" node in your database
//
//    // Example 1: Push `rental1` with a specific, identifiable key ("rental_ABC_123")
//    rentalsRef.child("rental_ABC_123").setValue(rental1)
//        .addOnSuccessListener {
//            Log.d("RTDB_PUSH", "rental_ABC_123 pushed successfully!")
//        }
//        .addOnFailureListener { e ->
//            Log.e("RTDB_PUSH", "Failed to push rental_ABC_123: ${e.message}", e)
//        }
//
//    // Example 2: Push `rental2` with another specific key ("rental_XYZ_456")
//    rentalsRef.child("rental_XYZ_456").setValue(rental2)
//        .addOnSuccessListener {
//            Log.d("RTDB_PUSH", "rental_XYZ_456 pushed successfully!")
//        }
//        .addOnFailureListener { e ->
//            Log.e("RTDB_PUSH", "Failed to push rental_XYZ_456: ${e.message}", e)
//        }
//
//    // Example 3: Push `rental3` and let Firebase generate a unique, time-based key
//    // This is often preferred when you don't have a natural unique ID for each item.
//    rentalsRef.push().setValue(rental3)
//        .addOnSuccessListener {
//            Log.d("RTDB_PUSH", "rental_DEF_789 (generated key) pushed successfully!")
//        }
//        .addOnFailureListener { e ->
//            Log.e("RTDB_PUSH", "Failed to push rental_DEF_789 (generated key): ${e.message}", e)
//        }
//}
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.car.data.car_api.SpeedViewModel
import com.example.car.data.car_api.dto.Rental
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private const val CHANNEL_ID = "speed_warning_channel"

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SpeedMonitorScreen(viewModel: SpeedViewModel = viewModel()) {
    val context = LocalContext.current
    val speedLimit = viewModel.speedLimit.collectAsState().value
    val vehicleData = viewModel.vehicleData.value

    // Create channel when screen loads
    LaunchedEffect(Unit) {
        createNotificationChannel(context)
        viewModel.collectData()
    }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = speedLimit.toString(),
            onValueChange = { viewModel.setSpeedLimit(it.toIntOrNull() ?: 0) },
            label = { Text("Set Max Speed (km/h)") },
            singleLine = true
        )
        Spacer(Modifier.height(16.dp))

        if (vehicleData != null) {
            Text("Ignition Status: ${vehicleData.ignitionStatus.value}")
            Text("Speed: ${vehicleData.speed.value} ${vehicleData.speed.unit}")

            // Push mock data if ignition = 2
            if (vehicleData.ignitionStatus.value == 2) {
                pushMockRentalData()
            }

            // Show warning if ignition = 4 or 5 and speed > limit
            if (vehicleData.ignitionStatus.value == 4 || vehicleData.ignitionStatus.value == 5){
                showSpeedWarningNotification(context, vehicleData.speed.value, speedLimit)
            }
        } else {
            Text("Fetching vehicle data...")
        }
    }
}

// ------------------- Notification Setup -------------------

private fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Speed Limit Warnings",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notifies when the speed limit is exceeded"
        }

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}

private fun showSpeedWarningNotification(context: Context, currentSpeed: Int, speedLimit: Int) {
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(android.R.drawable.ic_dialog_alert)
        .setContentTitle("⚠️ Speed Limit Exceeded")
        .setContentText("You are driving at $currentSpeed km/h (Limit: $speedLimit)")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
        ) {
            notify(1001, builder.build())
        } else {
            Log.w("Notification", "POST_NOTIFICATIONS permission not granted")
        }
    }
}

// ------------------- Your Mock Push Logic -------------------

fun pushMockRentalData() {
    lateinit var database: FirebaseDatabase
    lateinit var rentalsRef: DatabaseReference
    Log.d("RTDB_PUSH", "Attempting to push mock data...")

    val rental1 = Rental("cust_john_doe", "car_sedan_001", 100, "firebase_fcm", "active")
    val rental2 = Rental("cust_jane_smith", "car_suv_002", 90, "aws_sns", "scheduled")
    val rental3 = Rental("cust_john_doe", "car_truck_003", 110, "firebase_fcm", "active")

    database = FirebaseDatabase.getInstance("https://carr-2336b-default-rtdb.firebaseio.com")
    rentalsRef = database.getReference("rentals")

    rentalsRef.child("rental_ABC_123").setValue(rental1)
    rentalsRef.child("rental_XYZ_456").setValue(rental2)
    rentalsRef.push().setValue(rental3)
}
