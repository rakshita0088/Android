package com.example.car.data.car_api

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.car.ui.AppContextProvider
import com.google.android.gms.location.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import com.example.car.data.car_api.dto.VehicleData
import com.example.car.data.car_api.remote.GetVehicleData


class SpeedViewModel(application: Application) : AndroidViewModel(application) {

    private val getVehicleData = GetVehicleData(AppContextProvider.get())
    private val _vehicleData = mutableStateOf<VehicleData?>(null)
    val vehicleData: State<VehicleData?> = _vehicleData

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    private val _speed = MutableStateFlow(0)
    val speed: StateFlow<Int> = _speed

    private val _speedLimit = MutableStateFlow(0)
    val speedLimit: StateFlow<Int> = _speedLimit

    private val locationRequest = LocationRequest.create().apply {
        interval = 2000
        fastestInterval = 1000
        priority = Priority.PRIORITY_HIGH_ACCURACY
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            result.lastLocation?.let { loc ->
                val speedKmh = (loc.speed * 3.6).toInt()
                _speed.value = speedKmh
            }
        }
    }

    init {
        startLocationUpdates()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun collectData() {
        viewModelScope.launch {
            getVehicleData.fetchData().collect { vehicleData ->
                _vehicleData.value = vehicleData
            }
        }
    }

    @Suppress("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    fun setSpeedLimit(limit: Int) {
        _speedLimit.value = limit
    }

    override fun onCleared() {
        super.onCleared()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}