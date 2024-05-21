package com.example.wavesoffood.Fragment

import android.os.Bundle
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
import com.example.wavesoffood.R
import com.example.wavesoffood.adapter.PopularAdapter
import com.example.wavesoffood.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

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
        val foodName = listOf("Pasta", "Chicken", "Burger","Pepsi")
        val Price = listOf("35,000đ", "35,000đ", "33,000đ","12,000đ",)
        val popularFoodImages = listOf(
            R.drawable.menufood1,
            R.drawable.menufood2,
            R.drawable.menufood3,
            R.drawable.menufood6,)
        val adapter = PopularAdapter(foodName,Price,popularFoodImages)
            binding.PopularRecycleView.layoutManager = LinearLayoutManager(requireContext())
            binding.PopularRecycleView.adapter = adapter
    }

    companion object {
    }
}