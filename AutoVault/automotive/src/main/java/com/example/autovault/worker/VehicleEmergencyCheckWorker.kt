package com.example.autovault.worker

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.autovault.data.car_api.GetVehicleData
//import com.example.autovaultlistener.repository.VehicleDataRepository

class VehicleEmergencyCheckWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams){
    private val repository = GetVehicleData(context)

    @RequiresApi(Build.VERSION_CODES.P)
    override suspend fun doWork(): Result {
        return try {
            val health = repository.subscribeVehicleProperties()
            health.let {
                if (it.batteryLevel.value < 20) {
                    showBatteryLowNotification(context, it.batteryLevel.value)
                }
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private fun showBatteryLowNotification(context: Context, batteryLevel: Int) {
        val channelId = "vehicle_battery_alerts"
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Vehicle Battery Alerts",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Battery alerts from vehicle health"
            }
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_dialog_alert)
            .setContentTitle("Battery Low!")
            .setContentText("Battery is at $batteryLevel%. Please check vehicle.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        manager.notify(2001, notification)
    }
}