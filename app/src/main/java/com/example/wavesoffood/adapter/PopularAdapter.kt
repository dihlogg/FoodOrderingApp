package com.example.wavesoffood.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wavesoffood.DetailsActivity
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.adapter.PopularAdapter.PopularViewHolder
import com.example.wavesoffood.databinding.PopularItemBinding

class PopularAdapter (
    private val popularItemsName: List<FoodInfo>,
    private val requireContext : Context
) : RecyclerView.Adapter<PopularViewHolder>() {

    private val itemClickListener : MenuAdapter.OnClickListener?= null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.PopularViewHolder {
        val binding = PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.PopularViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = popularItemsName.size


    inner class PopularViewHolder(private val binding : PopularItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                foodNamePopular.text = popularItemsName[position].name
                pricePopular.text = popularItemsName[position].price.toString()
                Glide.with(foodImagePopular.context).load(popularItemsName[position].imageMenu).into(foodImagePopular)
            }
        }
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(position)
                }
                // set on click listener to open details
                val intent = Intent(requireContext, DetailsActivity::class.java)
                intent.putExtra("foodId", popularItemsName[position].id)
                intent.putExtra("foodName", popularItemsName[position].name)
                intent.putExtra("foodPrice", popularItemsName[position].price)
                intent.putExtra("foodImage", popularItemsName[position].imageMenu)
                intent.putExtra("foodImageDetails", popularItemsName[position].imageDetail)
                intent.putExtra("foodDescription", popularItemsName[position].description)
                intent.putExtra("foodIngredient", popularItemsName[position].ingredient)
                requireContext.startActivity(intent)
            }
        }
    }
}

