package com.example.wavesoffood.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.LoginActivity
import com.example.wavesoffood.Models.CartInfo
import com.example.wavesoffood.adapter.BuyAgainAdapter
import com.example.wavesoffood.databinding.FragmentHistoryBinding
import com.example.wavesoffood.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = requireContext()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val userId = LoginActivity.UserInfo?.id
        if (userId == null){
            // quay ve trang login
            val intent = Intent(getContext(), LoginActivity::class.java)
            startActivity(intent)
        }
        val call = ApiClient.apiService.GetTransactions(userId!!)
        call.enqueue(object : Callback<List<CartInfo>> {
            override fun onResponse(
                call: Call<List<CartInfo>>,
                response: Response<List<CartInfo>>
            ) {
                if (response.isSuccessful) {
                    var foodInfos = response.body()!!
                    if (response.body() != null) {
                        val adapter = BuyAgainAdapter(response.body()!!, requireContext())
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