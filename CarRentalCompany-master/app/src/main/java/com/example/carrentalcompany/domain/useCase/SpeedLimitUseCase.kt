package com.example.carrentalcompany.domain.useCase

import com.example.carrentalcompany.data.model.SpeedData
import com.example.carrentalcompany.data.repository.CustomerSpeedRepository
import javax.inject.Inject

class SpeedLimitUseCase@Inject constructor(
    private val speedLimitRepository: CustomerSpeedRepository
) {
    suspend fun checkSpeedAndNotify(speedData: SpeedData) {
        val maxSpeed = speedLimitRepository.getMaxSpeedForCustomer(speedData.customerId)
        if (speedData.currentSpeed > maxSpeed) {
            /// here current speed can be set in "speedData.currentSpeed"
            val token  = "firebaseToken"
            val message = "Speed limit exceeded! Current speed: ${speedData.currentSpeed} km/h"
            speedLimitRepository.sendNotification(message,token)
        }
    }
}
/*
viewModel.checkSpeed(speedData)*/
