package com.example.wavesoffood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.adapter.MenuAdapter
import com.example.wavesoffood.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMenuBottomSheetBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)

        binding.buttonBack.setOnClickListener {
            dismiss()
        }
        val foodInfos = listOf(
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
//        val menuFoodName = listOf("Pasta", "Chicken", "Burger", "Sandwich", "Salad", "Pepsi", "mirinda")
//        val menuItemPrice = listOf("35,000đ", "35,000đ", "33,000đ", "29,000đ", "19,000đ", "12,000đ", "12,000đ",)
//        val menuImage = listOf(
//            R.drawable.menufood1,
//            R.drawable.menufood2,
//            R.drawable.menufood3,
//            R.drawable.menufood4,
//            R.drawable.menufood5,
//            R.drawable.menufood6,
//            R.drawable.menufood7,
//        )

        val adapter = MenuAdapter(foodInfos, requireContext())
        binding.menuRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerview.adapter = adapter
        return binding.root
    }

    companion object {
    }
}
