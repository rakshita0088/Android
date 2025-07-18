package com.example.carrentalcompany.data.repository

interface CustomerSpeedRepository {
    suspend fun getMaxSpeedForCustomer(customerId: String): Float
    suspend fun sendNotification(message: String,token : String)
    suspend fun updateSpeedLimit(customerId: String, maxSpeed: Float)

}