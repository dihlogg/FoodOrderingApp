package com.example.wavesoffood.services

import com.example.wavesoffood.Models.FoodInfo
import retrofit2.Call
import retrofit2.http.GET

interface MyApiService {
    @GET("WeatherForecast")
    fun getFoodInfos(): Call<List<FoodInfo>>
}