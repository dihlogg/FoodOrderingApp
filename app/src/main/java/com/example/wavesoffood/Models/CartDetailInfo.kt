package com.example.wavesoffood.Models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class CartInfo(
    var id: String,
    var status: String,
    var dateOrder: String,
    var totalPrice: Double
) {
    lateinit var cartDetails: MutableList<CartDetailInfo>
}

class CartDetailInfo(
    var image: String,
    var foodName: String,
    var price: Double,
    var quantity: Int
) {
}

class CartInfoRequestDto() {
    var status = "NewOrder"
    var dateOrder: String = ""
    var userId : String =""
    lateinit var cartDetailDtos: MutableList<CartDetailRequestDtos>
}

class CartDetailRequestDtos(
    var foodId: String,
    var quantity: Int,
    var totalPrice: Double,
) {
}