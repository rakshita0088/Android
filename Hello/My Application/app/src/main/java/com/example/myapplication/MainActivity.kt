package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.mainui.ProductListScreen
import com.example.myapplication.mainui.ProductViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ProductViewModel()

        setContent {
            MyApplicationTheme{
                ProductListScreen(viewModel = viewModel)
            }
        }
    }
}
