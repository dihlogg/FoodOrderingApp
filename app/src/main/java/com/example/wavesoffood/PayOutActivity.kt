package com.example.wavesoffood

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.wavesoffood.Models.CartDetailRequestDtos
import com.example.wavesoffood.Models.CartInfoRequestDto
import com.example.wavesoffood.common.DateTimeCommonFunction
import com.example.wavesoffood.databinding.ActivityPayOutBinding
import com.example.wavesoffood.roomdb.dao.FoodInfoDao
import com.example.wavesoffood.roomdb.database.AppDatabase
import com.example.wavesoffood.services.ApiClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayOutActivity : AppCompatActivity() {

    private lateinit var _foodInfoDao: FoodInfoDao;
    lateinit var binding: ActivityPayOutBinding
    private var totalPrice: Double = 0.0

    //private lateinit var cartInfo : CartInfo
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appDatabase = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "my-database"
        ).allowMainThreadQueries().build()
        _foodInfoDao = appDatabase.FoodInfoDao()
        enableEdgeToEdge()
        binding = ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val totalPriceTextView = binding.PayOutTotalPrice
        totalPrice = intent.getDoubleExtra("totalPrice", 0.0)
        totalPriceTextView.text = totalPrice.toString()
        binding.PlaceMyOrderButton.setOnClickListener {
            val bottomSheetDialog = CongratsBottomSheetFragment()
            bottomSheetDialog.show(supportFragmentManager, "Order")
            postCartDetailInfo();
        }

        binding.imageButtonBack.setOnClickListener {
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun postCartDetailInfo() {
        val cartInfoRequest = CartInfoRequestDto()
        cartInfoRequest.dateOrder = DateTimeCommonFunction.getDateTimeCurrent()
        cartInfoRequest.cartDetailDtos = getFoodInfoFromRoom()
        val call = ApiClient.apiService.postCartDetailInfo(cartInfoRequest)
        call.enqueue(object : Callback<Boolean?> {
            override fun onResponse(
                call: Call<Boolean?>,
                response: Response<Boolean?>
            ) {
                if (response.isSuccessful) {
                    Log.d("postcart", response.message())
                    // Clear data DB
                    _foodInfoDao.clearAllFoodInfos()
                } else {
                    Log.d(
                        "postcart1",
                        "Post failed: ${response.message()} ${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<Boolean?>, t: Throwable) {
                Log.d("postcart2", t.toString())
            }
        })
    }

    private fun getFoodInfoFromRoom(): MutableList<CartDetailRequestDtos> {
        val foodInfodas = _foodInfoDao.getFoodInfos()
        val foodInfoModels = mutableListOf<CartDetailRequestDtos>()
        for (foodInfo in foodInfodas.reversed()) {
            val cartInfoRequestDto = CartDetailRequestDtos(
                foodId = foodInfo.id,
                quantity = foodInfo.quantity,
                totalPrice = foodInfo.price * foodInfo.quantity
            )
            foodInfoModels.add(cartInfoRequestDto)
        }
        return foodInfoModels;
    }
}