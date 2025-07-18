package com.example.carrentalcompany.data.model

// here we will check the maximum speed
data class CustomerSpeed(val customerId: String,
                         val maxSpeed: Float)


// here we will check the current speed
data class SpeedData(
    val customerId: String,
    val currentSpeed: Float
)
