package com.example.car.data.car_api.dto

data class Rental(
    val customerId: String? = null,
    val vehicleId: String? = null,
    val maxSpeedLimitKmph: Int? = null,
    val notificationChannel: String? = null,
    val rentalStatus: String? = null
)