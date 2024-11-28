package com.emirhan.yemekbook.service

import com.emirhan.yemekbook.model.foodData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class foodApiService {

    private val BASE_URL="https://raw.githubusercontent.com/"
    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(foodAPI::class.java)
    suspend fun getData():List<foodData>{
        return api.getFood()
    }
}