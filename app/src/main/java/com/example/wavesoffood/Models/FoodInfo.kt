package com.example.wavesoffood.Models

class FoodInfo(
    var id: String,
    var name: String,
    var price: Double,
    var imageMenu: String,
    var imageDetail: String,
    var description: String,
    var ingredient: String,
    var quantity: Int = 1
    ) {
    var newPrice: Double = 0.0
        get() {
            return price * quantity
        }
}