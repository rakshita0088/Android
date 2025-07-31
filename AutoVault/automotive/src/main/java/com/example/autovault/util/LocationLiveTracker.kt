package com.example.autovault.util
//
//class LocationLiveTracker {
//}
//package com.example.autovault.util
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.location.Location
//import android.os.Looper
//import com.google.android.gms.location.*
//
//class LocationLiveTracker(
//    context: Context,
//    private val onLocationUpdate: (Location) -> Unit
//) {
//    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
//
//    private val locationRequest = LocationRequest.create().apply {
//        interval = 5000
//        fastestInterval = 3000
//        priority = Priority.PRIORITY_HIGH_ACCURACY
//    }
//
//    private val locationCallback = object : LocationCallback() {
//        override fun onLocationResult(result: LocationResult) {
//            for (location in result.locations) {
//                onLocationUpdate(location)
//            }
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    fun startTracking() {
//        fusedLocationClient.requestLocationUpdates(
//            locationRequest,
//            locationCallback,
//            Looper.getMainLooper()
//        )
//    }
//
//    fun stopTracking() {
//        fusedLocationClient.removeLocationUpdates(locationCallback)
//    }
//}
//package com.example.autovault.location
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.location.Location
//import android.os.Looper
//import com.google.android.gms.location.*
//
//class LocationLiveTracker(
//    private val context: Context,
//    private val onLocationAvailable: (Location) -> Unit
//) {
//    private var fusedLocationProviderClient: FusedLocationProviderClient =
//        LocationServices.getFusedLocationProviderClient(context)
//
//    private val locationRequest = LocationRequest.create().apply {
//        interval = 10000
//        fastestInterval = 5000
//        priority = Priority.PRIORITY_HIGH_ACCURACY
//        maxWaitTime = 15000
//    }
//
//    private val locationCallback = object : LocationCallback() {
//        override fun onLocationResult(result: LocationResult) {
//            result.lastLocation?.let {
//                onLocationAvailable(it)
//                stopUpdates()
//            }
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    fun startTracking() {
//        fusedLocationProviderClient.requestLocationUpdates(
//            locationRequest,
//            locationCallback,
//            Looper.getMainLooper()
//        )
//    }
//
//    fun stopUpdates() {
//        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
//    }
//}
//package com.example.autovault.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

class LocationLiveTracker(
    private val context: Context,
    private val onLocationUpdate: (Location) -> Unit
) {
    private var locationManager: LocationManager? = null
    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            onLocationUpdate(location)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    @SuppressLint("MissingPermission")
    fun startTracking() {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager?.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000L,
            1f,
            locationListener
        )
    }

    fun stopTracking() {
        locationManager?.removeUpdates(locationListener)
    }
}
