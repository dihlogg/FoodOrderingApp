package com.example.wavesoffood.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.R
import com.example.wavesoffood.adapter.MenuAdapter
import com.example.wavesoffood.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter
    var foodInfos = listOf(
        FoodInfo(
            "1",
            "test1",
            13.0,
            "https://i.pinimg.com/originals/52/64/5f/52645facc7cd8e1aa56b9ffd3a67f082.jpg"
        ),
        FoodInfo(
            "2",
            "test2",
            14.0,
            "https://i.pinimg.com/originals/52/64/5f/52645facc7cd8e1aa56b9ffd3a67f082.jpg"
        )
    )
//    private val orignalMenuFoodName = listOf("Pasta", "Chicken", "Burger", "Sandwich", "Salab", "Pepsi", "Mirinda")
//
//    private val orignalMenuPrice = listOf("35,000đ", "35,000đ", "33,000đ", "29,000đ", "19,000đ", "12,000đ", "12,000đ",)
//
//    private val orignalMenuImage = listOf(
//        R.drawable.menufood1,
//        R.drawable.menufood2,
//        R.drawable.menufood3,
//        R.drawable.menufood4,
//        R.drawable.menufood5,
//        R.drawable.menufood6,
//        R.drawable.menufood7,
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

//    private val filteredMenuFoodName = mutableListOf<String>()
//    private val filteredMenuFoodPrice = mutableListOf<String>()
//    private val filteredMenuFoodImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        adapter = MenuAdapter(foodInfos, requireContext())
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        // set up search view
        setupSearchView()

        //Show All menu items
        showAllMenu()

        return binding.root
    }

    private fun showAllMenu() {
//        foodInfos.()
//        filteredMenuFoodPrice.clear()
//        filteredMenuFoodImage.clear()
//
//        filteredMenuFoodName.addAll(orignalMenuFoodName)
//        filteredMenuFoodPrice.addAll(orignalMenuPrice)
//        filteredMenuFoodImage.addAll(orignalMenuImage)

        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })
    }

    private fun filterMenuItems(query: String) {
//        filteredMenuFoodName.clear()
//        filteredMenuFoodPrice.clear()
//        filteredMenuFoodImage.clear()
//
//        orignalMenuFoodName.forEachIndexed {index, foodName ->
//            if (foodName.contains(query.toString(), ignoreCase = true)) {
//                filteredMenuFoodName.add(foodName)
//                filteredMenuFoodPrice.add(orignalMenuPrice[index])
//                filteredMenuFoodImage.add(orignalMenuImage[index])
//            }
//        }

        adapter.notifyDataSetChanged()

    }

    companion object {
    }
}
