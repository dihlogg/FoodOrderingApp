package com.example.wavesoffood.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wavesoffood.DetailsActivity
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.databinding.MenuItemBinding
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class MenuAdapter(private val menuItemsName: List<FoodInfo>,
                  private val requireContext : Context) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private val itemClickListener : OnClickListener ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int = menuItemsName.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class MenuViewHolder(private val binding : MenuItemBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(position)
                }
                // set on click listener to open details
                val intent = Intent(requireContext, DetailsActivity::class.java)
                intent.putExtra("foodId", menuItemsName[position].id)
                intent.putExtra("foodName", menuItemsName[position].name)
                intent.putExtra("foodPrice", menuItemsName[position].price)
                intent.putExtra("foodImage", menuItemsName[position].imageMenu)
                intent.putExtra("foodImageDetails", menuItemsName[position].imageDetail)
                intent.putExtra("foodDescription", menuItemsName[position].description)
                intent.putExtra("foodIngredient", menuItemsName[position].ingredient)
                requireContext.startActivity(intent)
            }
        }

        fun bind(position: Int) {
            binding.apply {
                menuFoodName.text = menuItemsName[position].name
                menuPrice.text = menuItemsName[position].price.toString()
                Glide.with(menuImage.context).load(menuItemsName[position].imageMenu).into(menuImage)
            }
        }
    }
    interface OnClickListener {
        fun onItemClick(position: Int)
    }
}



