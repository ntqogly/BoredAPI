package com.example.boredapi.network

import com.example.boredapi.model.BoredActivity
import retrofit2.http.GET

interface ApiService {

    @GET("activity/")
    suspend fun loadActivity(): BoredActivity
}