package com.example.wavesoffood.Models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class CartInfo(
    var status: String,
    var dateOrder: String,
    var totalPrice: Double
) {
    lateinit var cartDetail: MutableList<CartDetailInfo>
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
    var userId = "c52d998c-7e24-4fea-93f1-7662ffeb8d42"
    lateinit var cartDetailDtos: MutableList<CartDetailRequestDtos>
}

class CartDetailRequestDtos(
    var foodId: String,
    var quantity: Int,
    var totalPrice: Double,
) {
}