package com.example.autovault.ui
//
////import androidx.compose.material3.Text
////import androidx.compose.runtime.Composable
////
////class ServiceReminderScreen {
////    @Composable
////    fun ServiceReminderScreen () {
////        Text("Service Reminder Screen ")
////    }
////}
//import android.content.Context
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.work.*
//import androidx.work.WorkerParameters
//import java.util.concurrent.TimeUnit
//
//class ReminderWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
//    override fun doWork(): Result {
//        // Log reminder or trigger notification here
//        return Result.success()
//    }
//}
//
//@Composable
//fun ServiceRemindersScreen(context: Context) {
//    var lastService by remember { mutableStateOf("") }
//    var nextService by remember { mutableStateOf("") }
//
//    Column(Modifier.padding(16.dp)) {
//        OutlinedTextField(
//            value = lastService,
//            onValueChange = { lastService = it },
//            label = { Text("Last Service Date") }
//        )
//        OutlinedTextField(
//            value = nextService,
//            onValueChange = { nextService = it },
//            label = { Text("Next Service Due") }
//        )
//        Button(onClick = {
//            val request = PeriodicWorkRequestBuilder<ReminderWorker>(1, TimeUnit.DAYS)
//                .setInitialDelay(10, TimeUnit.SECONDS)
//                .build()
//            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
//                "ServiceReminder",
//                ExistingPeriodicWorkPolicy.REPLACE,
//                request
//            )
//        }) {
//            Text("Save Reminder")
//        }
//    }
//}
//package com.example.autovault.ui

//import android.app.DatePickerDialog
//import android.content.Context
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.work.*
//import com.example.autovault.ViewModel.ServiceReminderViewModel
//import com.example.autovault.worker.ReminderWorker
//import java.text.SimpleDateFormat
//import java.util.*
//import java.util.concurrent.TimeUnit
//
//@Composable
//fun ServiceRemindersScreen(
//    context: Context,
//    viewModel: ServiceReminderViewModel = viewModel()
//) {
//    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
//    var lastDate by remember { mutableStateOf("") }
//    var nextDate by remember { mutableStateOf("") }
//
//    val today = Calendar.getInstance()
//
//    fun showDatePicker(onDateSelected: (String) -> Unit) {
//        DatePickerDialog(
//            context,
//            { _, year, month, dayOfMonth ->
//                val picked = Calendar.getInstance()
//                picked.set(year, month, dayOfMonth)
//                onDateSelected(dateFormat.format(picked.time))
//            },
//            today.get(Calendar.YEAR),
//            today.get(Calendar.MONTH),
//            today.get(Calendar.DAY_OF_MONTH)
//        ).show()
//    }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text("Set Service Reminder", style = MaterialTheme.typography.headlineSmall)
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        Button(onClick = { showDatePicker { lastDate = it } }) {
//            Text(if (lastDate.isBlank()) "Select Last Service Date" else "Last: $lastDate")
//        }
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        Button(onClick = { showDatePicker { nextDate = it } }) {
//            Text(if (nextDate.isBlank()) "Select Next Service Due Date" else "Next: $nextDate")
//        }
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Button(onClick = {
//            viewModel.saveLog(lastDate, nextDate)
//
//            val work = PeriodicWorkRequestBuilder<ReminderWorker>(1, TimeUnit.DAYS)
//                .setInitialDelay(10, TimeUnit.SECONDS)
//                .build()
//
//            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
//                "ServiceReminderWorker",
//                ExistingPeriodicWorkPolicy.REPLACE,
//                work
//            )
//        }) {
//            Text("Save & Start Reminder")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = { viewModel.loadLogs() }) {
//            Text("View Service Log History")
//        }
//    }
//}


import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.work.*
import com.example.autovault.ViewModel.ServiceReminderViewModel
import com.example.autovault.worker.ReminderWorker
import java.util.concurrent.TimeUnit

@Composable
fun ServiceRemindersScreen(context: Context, viewModel: ServiceReminderViewModel) {
    var lastService by remember { mutableStateOf("") }
    var nextService by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = lastService,
            onValueChange = { lastService = it },
            label = { Text("Last Service Date") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nextService,
            onValueChange = { nextService = it },
            label = { Text("Next Service Due") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.saveServiceLog(lastService, nextService)

            val request = PeriodicWorkRequestBuilder<ReminderWorker>(1, TimeUnit.DAYS)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "ServiceReminder",
                ExistingPeriodicWorkPolicy.REPLACE,
                request
            )
        }) {
            Text("Save Reminder")
        }
    }
}
