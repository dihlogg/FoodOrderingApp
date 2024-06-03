package com.example.wavesoffood.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes.*
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.wavesoffood.MenuBottomSheetFragment
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.R
import com.example.wavesoffood.adapter.PopularAdapter
import com.example.wavesoffood.databinding.FragmentHomeBinding
import com.example.wavesoffood.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: PopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val call = ApiClient.apiService.getPopularFoods()
        call.enqueue(object : Callback<List<FoodInfo>> {
            override fun onResponse(
                call: Call<List<FoodInfo>>,
                response: Response<List<FoodInfo>>
            ) {
                if (response.isSuccessful) {
                    val foodInfos = response.body()!!
                    if (response.body() != null) {
                        adapter = PopularAdapter(foodInfos, requireContext())
                        binding.PopularRecycleView.layoutManager =
                            LinearLayoutManager(requireContext())
                        binding.PopularRecycleView.adapter = adapter
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

        binding.viewAllMenu.setOnClickListener {
            val bottomSheetFragment = MenuBottomSheetFragment()
            bottomSheetFragment.show(parentFragmentManager, "Test")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner1, FIT))
        imageList.add(SlideModel(R.drawable.banner2, FIT))
        imageList.add(SlideModel(R.drawable.banner3, FIT))

        val imageSliler = binding.imageSlider
        imageSliler.setImageList(imageList)
        imageSliler.setImageList(imageList, FIT)

        imageSliler.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val itemPosition = imageList[position]
                val itemMessage = "Selected Image $position"
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
    }
}