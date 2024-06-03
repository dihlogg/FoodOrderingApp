package com.example.wavesoffood.Models

class RegisterRequest(
) {
    var id: String? = null
    var userName: String? = null
    var userPassword: String? = null
    var userFullName: String? = null
    var userAddress: String? = null
    var userPhone: String? = null
}

class UpdateStatusCartInfoRequest(
) {
    var cartInfoId: String? = null
    var status: String? = null
}

