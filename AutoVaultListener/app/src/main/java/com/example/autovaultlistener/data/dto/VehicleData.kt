package com.example.autovaultlistener.data.dto

data class VehicleData(
    val batteryLevel: BatteryLevel = BatteryLevel(),
    val emergencyalert: EmergencyAlert = EmergencyAlert(),
)



