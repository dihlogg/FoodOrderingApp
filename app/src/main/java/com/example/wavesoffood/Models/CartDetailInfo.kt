package com.example.wavesoffood.Models

class CartInfo (
    var status: String,
    var dateOrder: String,
    var totalPrice: Double,
    var cartDetail: List<CartDetailInfo>
) {
}

class CartDetailInfo (
    var image: String,
    var foodName: String,
    var price: Double,
    var quantity: Double) {
}