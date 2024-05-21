package com.example.wavesoffood.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.adapter.MenuAdapter
import com.example.wavesoffood.databinding.FragmentSearchBinding
import com.example.wavesoffood.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val call = ApiClient.apiService.getFoodInfos()
        call.enqueue(object : Callback<List<FoodInfo>> {
            override fun onResponse(
                call: Call<List<FoodInfo>>,
                response: Response<List<FoodInfo>>
            ) {
                if (response.isSuccessful) {
                    val foodInfos = response.body()!!
                    if (response.body() != null) {
                        adapter = MenuAdapter(foodInfos, requireContext())
                        binding.menuRecyclerView.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.menuRecyclerView.adapter = adapter
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

        // set up search view
        setupSearchView()

        return binding.root
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //filterMenuItems(newText)
                return true
            }
        })
    }

    private fun filterMenuItems(query: String) {
        val call = ApiClient.apiService.searchFoodInfos(query)
        call.enqueue(object : Callback<List<FoodInfo>> {
            override fun onResponse(
                call: Call<List<FoodInfo>>,
                response: Response<List<FoodInfo>>
            ) {
                if (response.isSuccessful) {
                    val foodInfos = response.body()!!
                    if (response.body() != null) {
                        adapter = MenuAdapter(foodInfos, requireContext())
                        binding.menuRecyclerView.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.menuRecyclerView.adapter = adapter
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

    }

    companion object
}
