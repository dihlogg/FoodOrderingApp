package com.example.wavesoffood

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.Models.CartDetailInfo
import com.example.wavesoffood.adapter.CartDetailHistoryAdapter
import com.example.wavesoffood.databinding.ActivityHistoryDetailsBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class History_Details_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // receiv data
        val foodStatus = intent.getStringExtra("foodStatus")
        val foodDateOrder = intent.getStringExtra("foodDateOrder")
        val foodTotalPrice = intent.getDoubleExtra("foodTotalPrice", 0.0)
        val cartDetailsJson = intent.getStringExtra("cartDetails")

        val gson = Gson()
        val cartDetails: List<CartDetailInfo> = gson.fromJson(cartDetailsJson, object : TypeToken<List<CartDetailInfo>>() {}.type)

        binding.textViewStatus.text = foodStatus
        binding.textViewDateOrder.text = foodDateOrder
        binding.textViewTotalPrice.text = foodTotalPrice.toString()

        binding.historyDetailsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.historyDetailsRecyclerView.adapter = CartDetailHistoryAdapter(cartDetails)

        // Set up the back button
        binding.imageButtonBack.setOnClickListener {
            finish()
        }
    }
}