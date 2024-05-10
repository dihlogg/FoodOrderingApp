package com.example.wavesoffood.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.R
import com.example.wavesoffood.adapter.BuyAgainAdapter
import com.example.wavesoffood.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var binding : FragmentHistoryBinding
    private lateinit var buyAgainAdapter : BuyAgainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        val buyAgainFoodName = arrayListOf("Pasta", "Chicken", "Burger")
        val buyAgainFoodPrice = arrayListOf("35,000đ", "35,000đ", "33,000đ")
        val buyAgainFoodImage = arrayListOf(
            R.drawable.menufood1,
            R.drawable.menufood2,
            R.drawable.menufood3,
        )
        buyAgainAdapter = BuyAgainAdapter(buyAgainFoodName, buyAgainFoodPrice, buyAgainFoodImage)
        binding.BuyAgainRecyclerView.adapter = buyAgainAdapter
        binding.BuyAgainRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
    }
}