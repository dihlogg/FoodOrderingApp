package com.example.wavesoffood

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.wavesoffood.Models.CartInfo
import com.example.wavesoffood.databinding.ActivityHistoryDetailsBinding
class History_Details_Activity : AppCompatActivity() {

    lateinit var binding: ActivityHistoryDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHistoryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_history_details)

    }
}