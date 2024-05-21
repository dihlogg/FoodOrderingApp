package com.example.wavesoffood.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.PayOutActivity
import com.example.wavesoffood.adapter.CartAdapter
import com.example.wavesoffood.databinding.FragmentCartBinding
import com.example.wavesoffood.roomdb.database.AppDatabase

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        val appDatabase = Room.databaseBuilder(
            container!!.context,
            AppDatabase::class.java,
            "my-database"
        ).allowMainThreadQueries().build()
        // Example usage
        val FoodInfoDao = appDatabase.FoodInfoDao()
        val foodInfodas = FoodInfoDao.getFoodInfos()
        var foodInfoModels = mutableListOf<FoodInfo>()
        for (foodInfo in foodInfodas.reversed()) {
            var foodInfoModel = FoodInfo(foodInfo.id, foodInfo.name, foodInfo.price, foodInfo.imageMenu, foodInfo.imageDetails, foodInfo.description, foodInfo.ingredient )
            foodInfoModels.add(foodInfoModel)
        }
        val adapter = CartAdapter(foodInfoModels)
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        binding.proceedButton.setOnClickListener {
            val intent = Intent(requireContext(), PayOutActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    companion object
}