package com.example.car.data.car_api.dto

data class VehicleData(
    var ignitionStatus: IgnitionStatus = IgnitionStatus(),
    var speed: Speed = Speed(),
    var rental: Rental = Rental(),
)