package com.example.wavesoffood

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.wavesoffood.Models.LoginRequest
import com.example.wavesoffood.Models.RegisterRequest
import com.example.wavesoffood.databinding.ActivityLoginBinding
import com.example.wavesoffood.services.ApiClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UserInfo = null
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.donthaveButton.setOnClickListener {
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            val eGmail = binding.editTextTextEmailAddress.text.toString()
            val eUserPassword = binding.editTextTextPassword.text.toString()

            if (eGmail.isNotEmpty() && eUserPassword.isNotEmpty()) {
                loginUser(eGmail, eUserPassword)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

//        val passwordEditText = binding.editTextTextPassword
//        passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        val passwordEditText = binding.editTextTextPassword
        val passwordToggle = binding.passwordToggle

        passwordToggle.setOnClickListener {
            if (passwordEditText.transformationMethod is PasswordTransformationMethod) {
                // show password
                passwordEditText.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                passwordToggle.setImageResource(R.drawable.visibility)
            } else {
                // hide password
                passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                passwordToggle.setImageResource(R.drawable.visibility_off)
            }
            passwordEditText.setSelection(passwordEditText.text.length)
        }
    }

    private fun loginUser(eGmail: String, eUserPassword: String) {
        val userInfoRequest = LoginRequest()
        userInfoRequest.userName = eGmail
        userInfoRequest.passWord = eUserPassword
//        val gson = Gson()
//        val json = gson.toJson(userInfoRequest)
        val call = ApiClient.apiService.loginUser(userInfoRequest)
        call.enqueue(object : Callback<RegisterRequest> {
            override fun onResponse(
                call: Call<RegisterRequest>,
                response: Response<RegisterRequest>
            ) {
                if (response.isSuccessful) {
                    Log.d("Login", response.message())
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    UserInfo = response.body();
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.d(
                        "Login1",
                        "Login failed: ${response.message()} ${
                            response.errorBody()?.string()
                        }"
                    )
                    Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<RegisterRequest>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    companion object {
        var UserInfo: RegisterRequest? = null
    }
}