package com.example.carrentalcompany.data.repository

import com.example.carrentalcompany.utils.FirebaseService
import javax.inject.Inject

class SpeedLimitRepositoryImpl @Inject constructor(
    private val firebaseService: FirebaseService // here the FirebaseService will handle notifications
) : CustomerSpeedRepository  {

    private val customerSpeedLimits = mutableMapOf<String, Float>(
        "customer1" to 120f,
        "customer2" to 100f
    )

    override suspend fun getMaxSpeedForCustomer(customerId: String): Float {
            return customerSpeedLimits[customerId] ?: 100f
    }

    override suspend fun sendNotification(message: String, token: String) {
        firebaseService.sendNotification(message,token)
    }

    override suspend fun updateSpeedLimit(customerId: String, maxSpeed: Float) {
        customerSpeedLimits[customerId] = maxSpeed
    }

}