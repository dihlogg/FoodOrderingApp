package com.example.wavesoffood.services

import com.example.wavesoffood.Models.CartInfo
import com.example.wavesoffood.Models.FoodInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApiService {
    @GET("FoodInfo/GetFoodInfos")
    fun getFoodInfos(): Call<List<FoodInfo>>

    @GET("FoodInfo/SearchFoodInfos")
    fun searchFoodInfos(@Query("foodName") foodName: String): Call<List<FoodInfo>>
    @GET("CartInfo/GetTransactions/c52d998c-7e24-4fea-93f1-7662ffeb8d42")
    fun GetTransactions(): Call<List<CartInfo>>

    @GET("CartInfo/GetCartInfos")
    fun getCartInfos(): Call<List<CartInfo>>

    @GET("CartInfo/PostCartInfo")
    fun postCartInfo(@Body cartInfo: CartInfo): Call<List<CartInfo>>
}