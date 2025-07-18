package com.example.carrentalcompany.utils

import android.app.Notification
import android.os.Message
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class FirebaseService @Inject constructor()  {

    //here we will write complete logic of firebase
    suspend fun sendNotification(message: String, recipientToken: String) {
        try {
            val notification = Notification(
                /*title = "Speed Limit Alert",
                body = message*/
            )

            // Build the message to be sent to FCM
            val fcmMessage = Message.builder()
                .setNotification(notification)
                .setToken(recipientToken)  // Recipient's FCM token
                .build()

            // Send the message using Firebase Cloud Messaging
            val response = FirebaseMessaging.getInstance().send(fcmMessage)
            println("Successfully sent message: $response")
        } catch (e: Exception) {
            // Handle exceptions, e.g., network failure, invalid token
            println("Error sending Firebase notification: ${e.localizedMessage}")
        }
    }
}
