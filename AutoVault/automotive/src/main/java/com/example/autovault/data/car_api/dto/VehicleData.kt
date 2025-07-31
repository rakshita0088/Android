package com.example.autovault.data.car_api.dto

data class  VehicleData (
    val batteryLevel: BatteryLevel = BatteryLevel(),
    val flatTyre: FlatTyre = FlatTyre(),
    val overheatingEngine: OverheatingEngine = OverheatingEngine(),
    val brakeProblem: BrakeProblems = BrakeProblems(),
    val headlightFailure: HeadlightFailure = HeadlightFailure(),
//    val emergencyAlert: EmergencyAlert = EmergencyAlert()

    val emergencyAlert: EmergencyAlert = EmergencyAlert(
        message = "Emergency!! Help Me!!",
        longitude = 0.0,
        latitude = 0.0,
        contactNumber = "8446309202"
    )
)
