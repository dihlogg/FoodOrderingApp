package com.example.wavesoffood

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.wavesoffood.adapter.CartAdapter
import com.example.wavesoffood.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodName = intent.getStringExtra("MenuItemName")
        val foodImage = intent.getIntExtra("MenuItemImage", 0)

        binding.DetailsFoodName.text = foodName
        binding.DetailsFoodImage.setImageResource(foodImage)
        binding.imageButton.setOnClickListener {
            finish()
        }
        binding.addItemToCart.setOnClickListener {
            finish()
        }
    }
}