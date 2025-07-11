package com.example.myapplication.data.api

import com.example.myapplication.data.model.product
import retrofit2.Response
import retrofit2.http.GET

    interface ApiService {
        @GET("products")
        suspend fun getProducts(): Response<List<product>>
    }
