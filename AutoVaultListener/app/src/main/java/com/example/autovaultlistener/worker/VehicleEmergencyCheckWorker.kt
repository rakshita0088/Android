package com.example.autovaultlistener.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.autovaultlistener.repository.VehicleDataRepository
class VehicleEmergencyCheckWorker(private val context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams)
{
    private val repository = VehicleDataRepository()

    override suspend fun doWork(): Result {
        return try {
            val health = repository.fetchHealthStatus()
            health?.let {
                if (it.batteryLevel.value < 50) {
                    showBatteryLowNotification(context, it.batteryLevel.value)

                    sendSMS(it.emergencyalert.contactNumber, it.emergencyalert.message)
                }

//                if (it.emergencyalert.isButtonClicked == "Yes") {
//                    val msg = "${it.emergencyalert.message}\nLocation: ${it.emergencyalert.latitude} & ${it.emergencyalert.longitude}"
//                    sendSMS(it.emergencyalert.contactNumber, it.emergencyalert.message)
//
//
//                    // ✅ Mark SOS as handled so it doesn’t send again
//                    it.emergencyalert.isButtonClicked = "No"
//
//                    // ✅ Update the cloud with "No"
//                    repository.updateHealthStatus(it)
//                }
//                if (it.emergencyalert.isButtonClicked == "Yes") {
//                    val msg = "${it.emergencyalert.message}\nLocation: ${it.emergencyalert.latitude}, ${it.emergencyalert.longitude}"
//                    sendSMS(it.emergencyalert.contactNumber, it.emergencyalert.message)
//
//                    it.emergencyalert.isButtonClicked = "No"
//                    repository.updateHealthStatus(it)
//                }
                if (it.emergencyalert.isButtonClicked == "Yes") {
                    val lat = it.emergencyalert.latitude
                    val lon = it.emergencyalert.longitude
                    val msg = "${it.emergencyalert.message}\nLocation: https://maps.google.com/?q=$lat,$lon"

                    sendSMS(it.emergencyalert.contactNumber, msg)

                    it.emergencyalert.isButtonClicked = "No"
                    repository.updateHealthStatus(it)
                }



            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

//    private fun sendSMS(phoneNumber: String, message: String) {
//        val smsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            context.getSystemService(SmsManager::class.java)
//        } else {
//            SmsManager.getDefault()
//        }
//        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
//    }
private fun sendSMS(phoneNumber: String, message: String) {
    try {
        val smsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            context.getSystemService(SmsManager::class.java)
        } else {
            SmsManager.getDefault()
        }

        smsManager.sendTextMessage(phoneNumber, null, message, null, null)

    } catch (e: Exception) {

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
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("Battery Low!")
            .setContentText("Battery is at $batteryLevel%. Please check vehicle.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        manager.notify(2001, notification)
    }
}


//class VehicleEmergencyCheckWorker(private val context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams)
//{
//    private val repository = VehicleDataRepository()
//
//    override suspend fun doWork(): Result {
//        return try {
//            val health = repository.fetchHealthStatus()
//            health?.let {
//                if (it.batteryLevel.value < 50) {
//                    showBatteryLowNotification(context, it.batteryLevel.value)
//                    sendSMS(it.emergencyalert.contactNumber, it.emergencyalert.message)
//                }
//
////                if (it.emergencyalert.isButtonClicked=="Yes"){
////                    val String = "${it.emergencyalert.message} Location: ${it.emergencyalert.latitude} & ${it.emergencyalert.longitude}"
////                    sendSMS(it.emergencyalert.contactNumber, String)
////                    it.emergencyalert.isButtonClicked="No"
////                }
//                if (it.emergencyalert.isButtonClicked == "Yes") {
//                    val msg = "${it.emergencyalert.message}\nLocation: ${it.emergencyalert.latitude}, ${it.emergencyalert.longitude}"
//                    sendSMS(it.emergencyalert.contactNumber, msg)
//
//                    // ✅ Mark SOS as handled so it doesn’t send again
//                    it.emergencyalert.isButtonClicked = "No"
//
//                    // ✅ Update the cloud with "No"
//                    repository.updateHealthStatus(it)
//                }
////                if (it.emergencyalert.isButtonClicked == "Yes") {
////                    val lat = it.emergencyalert.latitude
////                    val lon = it.emergencyalert.longitude
////                    val msg = "${it.emergencyalert.message}\nLocation: https://maps.google.com/?q=$lat,$lon"
////
////                    val contacts = it.emergencyalert.contactNumber
////                    for (number in contact) {
////                        sendSMS(number, msg)
////                    }
////
////                    it.emergencyalert.isButtonClicked = "No"
////                    repository.updateHealthStatus(it)
////                }
//
//            }
//            Result.success()
//        } catch (e: Exception) {
//            Result.retry()
//        }
//    }
//
////    private fun sendSMS(phoneNumber: String, message: String) {
////        val smsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
////            context.getSystemService(SmsManager::class.java)
////        } else {
////            SmsManager.getDefault()
////        }
////        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
////    }
//private fun sendSMS(phoneNumber: String, message: String) {
//    try {
//        val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            context.getSystemService(SmsManager::class.java)
//        } else {
//            SmsManager.getDefault()
//        }
//
//        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
//
//        Toast.makeText(context, "SMS sent to $phoneNumber", Toast.LENGTH_SHORT).show()
//    } catch (e: Exception) {
//        Toast.makeText(context, "Failed to send SMS: ${e.message}", Toast.LENGTH_LONG).show()
//        e.printStackTrace()
//    }
//}
//
//
//    private fun showBatteryLowNotification(context: Context, batteryLevel: Int) {
//        val channelId = "vehicle_battery_alerts"
//        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                channelId,
//                "Vehicle Battery Alerts",
//                NotificationManager.IMPORTANCE_HIGH
//            ).apply {
//                description = "Battery alerts from vehicle health"
//            }
//            manager.createNotificationChannel(channel)
//        }
//
//        val notification = NotificationCompat.Builder(context, channelId)
//            .setSmallIcon(android.R.drawable.ic_dialog_alert)
//            .setContentTitle("Battery Low!")
//            .setContentText("Battery is at $batteryLevel%. Please check vehicle.")
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setAutoCancel(true)
//            .build()
//
//        manager.notify(2001, notification)
//    }
//}