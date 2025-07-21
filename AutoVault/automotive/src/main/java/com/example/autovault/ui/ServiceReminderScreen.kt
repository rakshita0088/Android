package com.example.autovault.ui

//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//
//class ServiceReminderScreen {
//    @Composable
//    fun ServiceReminderScreen () {
//        Text("Service Reminder Screen ")
//    }
//}
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.work.*
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class ReminderWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        // Log reminder or trigger notification here
        return Result.success()
    }
}

@Composable
fun ServiceRemindersScreen(context: Context) {
    var lastService by remember { mutableStateOf("") }
    var nextService by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = lastService,
            onValueChange = { lastService = it },
            label = { Text("Last Service Date") }
        )
        OutlinedTextField(
            value = nextService,
            onValueChange = { nextService = it },
            label = { Text("Next Service Due") }
        )
        Button(onClick = {
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