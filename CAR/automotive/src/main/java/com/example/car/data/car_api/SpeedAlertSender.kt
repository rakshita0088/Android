//package com.example.car.data
//
//
//import android.location.Location
//import okhttp3.*
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import org.json.JSONObject
//
//object SpeedAlertSender {
//
//    private const val SERVER_KEY = "YOUR_FCM_SERVER_KEY"
//    private const val OWNER_TOKEN = "OWNER_DEVICE_FCM_TOKEN"
//
//    fun sendAlertToOwner(location: Location, speed: Int) {
//        val message = "Driver exceeded speed: $speed km/h\nLocation: https://maps.google.com/?q=${location.latitude},${location.longitude}"
//
//        val json = JSONObject().apply {
//            put("to", OWNER_TOKEN)
//            put("notification", JSONObject().apply {
//                put("title", "Speed Alert!")
//                put("body", message)
//            })
//        }
//
//        val request = Request.Builder()
//            .url("https://fcm.googleapis.com/fcm/send")
//            .addHeader("Authorization", "key=$SERVER_KEY")
//            .addHeader("Content-Type", "application/json")
//            .post(RequestBody.create("application/json".toMediaTypeOrNull(), json.toString()))
//            .build()
//
//        OkHttpClient().newCall(request).execute()
//    }
//}


