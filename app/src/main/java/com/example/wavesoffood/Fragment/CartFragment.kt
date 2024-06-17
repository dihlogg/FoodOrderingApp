package com.example.wavesoffood.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.wavesoffood.LoginActivity
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.PayOutActivity
import com.example.wavesoffood.adapter.CartAdapter
import com.example.wavesoffood.databinding.FragmentCartBinding
import com.example.wavesoffood.interfaces.IClickListener
import com.example.wavesoffood.roomdb.dao.FoodInfoDao
import com.example.wavesoffood.roomdb.database.AppDatabase

class CartFragment : Fragment(), IClickListener {

    private lateinit var binding: FragmentCartBinding
    private lateinit var _foodInfoDao: FoodInfoDao;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        val appDatabase = Room.databaseBuilder(
            container!!.context,
            AppDatabase::class.java,
            "my-database"
        ).allowMainThreadQueries().build()
        _foodInfoDao = appDatabase.FoodInfoDao()
        var userId = LoginActivity.UserInfo?.id
        if (userId == null){
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
        Log.d("UserId", userId!!)
        val foodInfodas = _foodInfoDao.getFoodInfos(userId)
        val foodInfoModels = mutableListOf<FoodInfo>()
        for (foodInfo in foodInfodas.reversed()) {
            val foodInfoModel = FoodInfo(
                foodInfo.id,
                foodInfo.name,
                foodInfo.price,
                foodInfo.imageMenu,
                foodInfo.imageDetails,
                foodInfo.description,
                foodInfo.ingredient,
                foodInfo.quantity,
                foodInfo.foodQuantity
            )
            foodInfoModels.add(foodInfoModel)
        }
        val adapter = CartAdapter(foodInfoModels, this, requireContext())
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        binding.proceedButton.setOnClickListener {
            val intent = Intent(requireContext(), PayOutActivity::class.java).apply {
                putExtra("totalPrice", adapter.totalPrice)
            }
            startActivity(intent)
        }

        binding.totalPriceTextView.text = adapter.totalPrice.toString()

        return binding.root
    }

    override fun onClickListener(totalNewPrice: Double) {
        binding.totalPriceTextView.text = totalNewPrice.toString()
    }

    override fun updateFoodInfoListener(id: String, quantity: Int) {
        val itemUpdate = _foodInfoDao.getFoodInfo(id, LoginActivity.UserInfo?.id!!);
        itemUpdate.quantity = quantity
        _foodInfoDao.updateFood(itemUpdate);
    }

    override fun deleteFoodInfoListener(id: String) {
        val itemDelete = _foodInfoDao.getFoodInfo(id, LoginActivity.UserInfo?.id!!);
        _foodInfoDao.deleteFood(itemDelete);
    }
}