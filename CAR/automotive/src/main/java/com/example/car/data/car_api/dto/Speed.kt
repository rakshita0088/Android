package com.example.car.data.car_api.dto

data class Speed (
    val propertyId: Int = 0,
    val status: String = "Unknown",
    val unit: String = "percent",
    val value: Int = 0
)