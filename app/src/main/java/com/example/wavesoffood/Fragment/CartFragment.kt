package com.example.wavesoffood.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.PayOutActivity
import com.example.wavesoffood.adapter.CartAdapter
import com.example.wavesoffood.databinding.FragmentCartBinding
import com.example.wavesoffood.services.ApiClient
import com.example.wavesoffood.services.MyApiService
import com.example.wavesoffood.services.MyCertificatePinner
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        val call = ApiClient.apiService.getFoodInfos()
        var foodInfos = listOf<FoodInfo>();
        call.enqueue(object : Callback<List<FoodInfo>> {
            override fun onResponse(call: Call<List<FoodInfo>>, response: Response<List<FoodInfo>>) {
                if (response.isSuccessful) {
                    foodInfos = response.body()!!
                    if(response.body() != null) {
                        val adapter = CartAdapter(response.body()!!)
                        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                        binding.cartRecyclerView.adapter = adapter
                    }

                    Log.d("testing", response.message())
                } else {
                    Log.d("testing1", response.message() + response.errorBody())
                }
            }

            override fun onFailure(call: Call<List<FoodInfo>>, t: Throwable) {
                Log.d("testing2", t.toString())
            }
        })



        binding.proceedButton.setOnClickListener {
            val intent = Intent(requireContext(), PayOutActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    companion object {
    }
}