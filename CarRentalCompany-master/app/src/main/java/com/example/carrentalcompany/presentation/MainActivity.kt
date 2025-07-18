package com.example.carrentalcompany.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.carrentalcompany.data.model.SpeedData
import com.example.carrentalcompany.presentation.ui.theme.CarRentalCompanyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarRentalCompanyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                      val viewModel: SpeedLimitViewModel = hiltViewModel()
                    SpeedLimitScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun SpeedLimitScreen(viewModel: SpeedLimitViewModel) {
    val speedData = SpeedData(customerId = "customer1", currentSpeed = 130f)
    viewModel.checkSpeed(speedData)
}

