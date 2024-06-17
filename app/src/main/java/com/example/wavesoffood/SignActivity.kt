package com.example.wavesoffood

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.wavesoffood.Models.RegisterRequest
import com.example.wavesoffood.databinding.ActivitySignBinding
import com.example.wavesoffood.services.ApiClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignActivity : AppCompatActivity() {
    private val binding: ActivitySignBinding by lazy {
        ActivitySignBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.alreadyhaveButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.registerButton.setOnClickListener {
            val eFullName = binding.editTextTextName.text.toString()
            val eGmail = binding.editTextTextEmailAddress.text.toString()
            val eUserPassword = binding.editTextTextPassword.text.toString()
            val eUserAddress = binding.editTextTextAddress.text.toString()
            val eUserPhone = binding.editTextTextPhone.text.toString()

            if (eFullName.isNotEmpty() && eGmail.isNotEmpty() && eUserPassword.isNotEmpty() && eUserAddress.isNotEmpty() && eUserPhone.isNotEmpty()) {
                registerUser(eFullName, eGmail, eUserPassword, eUserAddress, eUserPhone)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(
        eGmail: String,
        eFullName: String,
        eUserPassword: String,
        eUserAddress: String,
        eUserPhone: String
    ) {
        val userInfoRequest = RegisterRequest()
        userInfoRequest.userName = eGmail
        userInfoRequest.userPassword = eUserPassword
        userInfoRequest.userFullName = eFullName
        userInfoRequest.userAddress = eUserAddress
        userInfoRequest.userPhone = eUserPhone
        val gson = Gson()
        val json = gson.toJson(userInfoRequest)
        val call = ApiClient.apiService.postUserInfo(userInfoRequest)
        call.enqueue(object : Callback<Boolean?> {
            override fun onResponse(
                call: Call<Boolean?>,
                response: Response<Boolean?>
            ) {
                if (response.isSuccessful) {
                    Log.d("Registration", response.message())
                    Toast.makeText(
                        this@SignActivity,
                        "Registration Successful",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                    val intent = Intent(this@SignActivity, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.d(
                        "Registration1",
                        "Registration failed: ${response.message()} ${
                            response.errorBody()?.string()
                        }"
                    )
                    Toast.makeText(this@SignActivity, "Registration Failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Boolean?>, t: Throwable) {
                Toast.makeText(this@SignActivity, "Error: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}