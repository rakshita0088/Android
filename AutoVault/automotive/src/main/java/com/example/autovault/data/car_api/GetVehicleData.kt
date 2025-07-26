package com.example.autovault.data.car_api


import android.car.VehiclePropertyIds
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.autovault.data.car_api.dto.BatteryLevel
import com.example.autovault.data.car_api.dto.BrakeProblems
import com.example.autovault.data.car_api.dto.FlatTyre
import com.example.autovault.data.car_api.dto.HeadlightFailure
import com.example.autovault.data.car_api.dto.OverheatingEngine
import com.example.autovault.data.car_api.dto.VehicleData
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.resume


class GetVehicleData(private val context: Context
) {

    private lateinit var batteryLevel: BatteryLevel

    @RequiresApi(Build.VERSION_CODES.P)
    suspend fun subscribeVehicleProperties(): VehicleData =
        withTimeoutOrNull(5000) {
            suspendCancellableCoroutine { cont ->
                var received = false
                lateinit var  carProperty: CarProperty
                carProperty = CarProperty.Builder(context)
                    .addProperty(VehiclePropertyIds.EV_BATTERY_LEVEL)
                    .addProperty((VehiclePropertyIds.DOOR_POS))
                    .setCallBack { propertyId, value ->
                        if (propertyId == VehiclePropertyIds.EV_BATTERY_LEVEL && value is Int) {
                            val batteryLevel = BatteryLevel(
                                propertyId = VehiclePropertyIds.EV_BATTERY_LEVEL,
                                status = "OK",
                                value = value,
                                unit = "%"
                            )

                            if (!received && cont.isActive) {
                                received = true

                                try {
                                    cont.resume(VehicleData(batteryLevel))
                                }catch (e: Exception) {
                                    Log.e("CarProperty", "Subscription creation failed: ${e.message}", e)
                                }
                                carProperty.stopListening()
                            }
                        }
                    }
                    .build()

                cont.invokeOnCancellation {
                    carProperty.stopListening()
                }

                carProperty.startListening()
            }
        } ?: VehicleData(
            batteryLevel = BatteryLevel(
                propertyId = VehiclePropertyIds.EV_BATTERY_LEVEL,
                status = "Unknown",
                value = 10,
                unit = "%"
            ),
            flatTyre = FlatTyre(
                value = 35,
                unit = "pascal"
            ),
            overheatingEngine = OverheatingEngine(
                status = "Critical",
                unit = "°C",
                value = 120
            ),
            brakeProblem = BrakeProblems(
                status = "Worn",
                unit = "%",
                value = 75
            ),
            headlightFailure = HeadlightFailure(
                status = "Failed",
                unit = "%",
                value = 100
            )
        )

}