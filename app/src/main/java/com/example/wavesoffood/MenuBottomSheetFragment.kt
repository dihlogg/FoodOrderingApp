package com.example.wavesoffood

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.adapter.MenuAdapter
import com.example.wavesoffood.databinding.FragmentMenuBottomSheetBinding
import com.example.wavesoffood.services.ApiClient
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMenuBottomSheetBinding

    private lateinit var adapter: MenuAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)

        binding.buttonBack.setOnClickListener {
            dismiss()
        }
        val call = ApiClient.apiService.getFoodInfos()
        call.enqueue(object : Callback<List<FoodInfo>> {
            override fun onResponse(
                call: Call<List<FoodInfo>>,
                response: Response<List<FoodInfo>>
            ) {
                if (response.isSuccessful) {
                    var foodInfos = response.body()!!
                    if (response.body() != null) {
                        adapter = MenuAdapter(foodInfos, requireContext())
                        binding.menuRecyclerview.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.menuRecyclerview.adapter = adapter
                    }
                    Log.d("testing", response.message())
                } else {
                    Log.d("testing1", "error:" + response.code() + ": " + response.message())
                }
            }

            override fun onFailure(call: Call<List<FoodInfo>>, t: Throwable) {
                Log.d("testing2", t.message.toString())
            }
        })

        return binding.root
    }

    companion object
}
