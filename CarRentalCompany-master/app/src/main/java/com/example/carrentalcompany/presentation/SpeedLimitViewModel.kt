package com.example.carrentalcompany.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrentalcompany.data.model.SpeedData
import com.example.carrentalcompany.data.repository.CustomerSpeedRepository
import com.example.carrentalcompany.domain.useCase.SpeedLimitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpeedLimitViewModel @Inject constructor(
    private val speedLimitUseCase: SpeedLimitUseCase,
    private val repository: CustomerSpeedRepository
) : ViewModel() {
    private val _speedCheckResult = MutableLiveData<String>()
    val speedCheckResult: LiveData<String> = _speedCheckResult
    var maxSpeed by mutableStateOf(100f)

    // here we will check the Speed and get the value in UI
    fun checkSpeed(speedData: SpeedData) {
        viewModelScope.launch {
            try {
                // here will check the current speed
                speedLimitUseCase.checkSpeedAndNotify(speedData)
                _speedCheckResult.value = "Speed check completed"
            } catch (e: Exception) {
                _speedCheckResult.value = "Error: ${e.message}"
            }
        }
    }

    // here we will update the maximumSpeed and get the value in UI
    fun updateMaxSpeed(customerId: String, newSpeed: Float) {
        viewModelScope.launch {
            repository.updateSpeedLimit(customerId, newSpeed)
            maxSpeed = newSpeed
        }
    }
}
