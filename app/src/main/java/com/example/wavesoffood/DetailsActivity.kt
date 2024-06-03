package com.example.wavesoffood

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.wavesoffood.databinding.ActivityDetailsBinding
import com.example.wavesoffood.roomdb.database.AppDatabase
import com.example.wavesoffood.roomdb.entity.FoodInfoRoom


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

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
        val foodQuantity = intent.getStringExtra("foodQuantity")

        binding.DetailsFoodName.text = foodName
        binding.DescriptionTextView.text = foodDescription
        binding.IngredientsTextView.text = foodIngredient
        binding.historyItemQuantity.text = foodPrice.toString()
        binding.FoodPriceTextView.text = foodQuantity
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
            val foodInfoDao = appDatabase.FoodInfoDao()
            val existFoodInfo = foodInfoDao.getFoodInfo(foodId!!, LoginActivity.UserInfo?.id!!)
            if (existFoodInfo != null) {
                existFoodInfo.quantity += 1
                foodInfoDao.updateFood(existFoodInfo)
            } else {
                val newFoodInfo = FoodInfoRoom(
                    id = foodId,
                    name = foodName!!,
                    price = foodPrice,
                    imageMenu = foodImage!!,
                    imageDetails = foodImageDetails!!,
                    description = foodDescription!!,
                    ingredient = foodIngredient!!,
                    quantity = 1,
                    foodQuantity = foodQuantity!!,
                    LoginActivity.UserInfo?.id
                )
                foodInfoDao.insertFood(newFoodInfo)
            }
            finish()
        }
    }
}