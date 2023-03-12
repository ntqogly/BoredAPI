package com.example.boredapi.model

import com.example.boredapi.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {

    companion object {
        private const val BASE_URL = "https://www.boredapi.com/api/"


        fun getApiService(): ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build()
            return retrofit.create(ApiService::class.java)
        }
    }
}