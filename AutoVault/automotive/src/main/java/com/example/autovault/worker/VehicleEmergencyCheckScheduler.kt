package com.example.autovault.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

fun VehicleEmergencyCheckScheduler(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<VehicleEmergencyCheckWorker>(
        15, TimeUnit.MINUTES
    ).build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
    "vehicle_health_worker",
    ExistingPeriodicWorkPolicy.KEEP,
    workRequest
    )
}