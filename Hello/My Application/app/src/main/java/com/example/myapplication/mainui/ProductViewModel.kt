package com.example.myapplication.mainui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.product
import com.example.myapplication.data.repository.ProductRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.*


class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()

    var products by mutableStateOf<List<product>>(emptyList())
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        isLoading = true
        errorMessage = null
        viewModelScope.launch {
            try {
                val response = repository.getProducts()
                if (response != null) {
                    products =response
                } else {
                    errorMessage = "No data found."
                }
            } catch (e: Exception) {
                errorMessage = "Something went wrong: ${e.localizedMessage}"
            }
            isLoading = false
        }
    }
}


