package com.example.wavesoffood

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.wavesoffood.adapter.CartAdapter
import com.example.wavesoffood.databinding.ActivityDetailsBinding
import com.example.wavesoffood.roomdb.database.AppDatabase


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodId = intent.getStringExtra("foodId")
        val foodName = intent.getStringExtra("foodName")
        val foodPrice = intent.getDoubleExtra("foodPrice", 0.0)
        val foodImage = intent.getStringExtra("foodImage")
        val foodImageDetails = intent.getStringExtra("foodImageDetails")
        val foodDescription = intent.getStringExtra("foodDescription")
        val foodIngredient = intent.getStringExtra("foodIngredient")

        binding.DetailsFoodName.text = foodName
        binding.DescriptionTextView.text = foodDescription
        binding.IngredientsTextView.text = foodIngredient
        // Use Glide get image url
        if (!foodImage.isNullOrEmpty()) {
            Glide.with(this)
                .load(foodImage)
                .into(binding.DetailsFoodImage)
        } else {
            Log.e("DetailsActivity", "foodImage URL is null or empty")
        }
        binding.imageButton.setOnClickListener {
            finish()
        }
        binding.addItemToCart.setOnClickListener {
            val appDatabase = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "my-database"
            ).allowMainThreadQueries().build()
            // Example usage
            val FoodInfoDao = appDatabase.FoodInfoDao()
            val newFoodInfo = com.example.wavesoffood.roomdb.entity.FoodInfo(
                id = foodId!!,
                name = foodName!!,
                price = foodPrice,
                imageMenu = foodImage!!,
                imageDetails = foodImageDetails!!,
                description = foodDescription!!,
                ingredient =  foodIngredient!!)
            FoodInfoDao.insertFood(newFoodInfo)
            finish()
        }
    }
}