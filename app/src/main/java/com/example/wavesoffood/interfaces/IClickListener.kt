package com.example.wavesoffood.interfaces

interface IClickListener {
    fun onClickListener(totalNewPrice: Double)
    fun updateFoodInfoListener(id: String, quantity: Int)
    fun deleteFoodInfoListener(id: String)
}