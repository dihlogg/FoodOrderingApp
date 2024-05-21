package com.example.wavesoffood.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.History_Details_Activity
import com.example.wavesoffood.Models.CartInfo
import com.example.wavesoffood.R
import com.example.wavesoffood.adapter.BuyAgainAdapter
import com.example.wavesoffood.databinding.FragmentHistoryBinding
import com.example.wavesoffood.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var adapter: BuyAgainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val call = ApiClient.apiService.GetTransactions()
        call.enqueue(object : Callback<List<CartInfo>> {
            override fun onResponse(
                call: Call<List<CartInfo>>,
                response: Response<List<CartInfo>>
            ) {
                if (response.isSuccessful) {
                    var foodInfos = response.body()!!
                    if (response.body() != null) {
                        val adapter = BuyAgainAdapter(response.body()!!)
                        binding.historyRecyclerView.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.historyRecyclerView.adapter = adapter
                    }
                    Log.d("testing", response.message())
                } else {
                    Log.d("testing1", "error:" + response.code() + ": " + response.message())
                }
            }

            override fun onFailure(call: Call<List<CartInfo>>, t: Throwable) {
                Log.d("testing2", t.message.toString())
            }
        })

        return binding.root
    }

    companion object {
    }
}