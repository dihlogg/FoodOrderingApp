package com.example.wavesoffood.services

import com.example.wavesoffood.Models.CartInfo
import com.example.wavesoffood.Models.CartInfoRequestDto
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.Models.LoginRequest
import com.example.wavesoffood.Models.RegisterRequest
import com.example.wavesoffood.Models.UpdateStatusCartInfoRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApiService {
    @GET("FoodInfo/GetFoodInfos")
    fun getFoodInfos(): Call<List<FoodInfo>>

    @GET("FoodInfo/SearchFoodInfos")
    fun searchFoodInfos(@Query("foodName") foodName: String): Call<List<FoodInfo>>

    @GET("CartInfo/GetTransactions/{userId}")
    fun GetTransactions(@Path("userId") userId: String): Call<List<CartInfo>>

    @GET("CartInfo/GetCartInfos")
    fun getCartInfos(): Call<List<CartInfo>>

    @Headers("Content-Type: application/json")
    @POST("CartInfo/PostCartDetailInfo")
    fun postCartDetailInfo(@Body cartDetailInfo: CartInfoRequestDto): Call<Boolean?>

    @Headers("Content-Type: application/json")
    @POST("UserInfo/PostUserInfo")
    fun postUserInfo(@Body registerRequest: RegisterRequest): Call<Boolean?>

    @Headers("Content-Type: application/json")
    @POST("UserInfo/LoginUser")
    fun loginUser(@Body loginRequest: LoginRequest): Call<RegisterRequest>

    @GET("FoodInfo/GetPopularFoods")
    fun getPopularFoods(): Call<List<FoodInfo>>

    @Headers("Content-Type: application/json")
    @PUT("CartInfo/UpdateStatusCartInfo")
    fun updateStatusCartInfo(@Body updateStatus: UpdateStatusCartInfoRequest): Call<Boolean?>
}
