package com.example.wavesoffood.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
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
                intent.putExtra("MenuItemName", menuItemsName.get(position).foodName)
                intent.putExtra("MenuItemImage", menuItemsName.get(position).image)
                requireContext.startActivity(intent)
            }
        }

        fun bind(position: Int) {
            binding.apply {
                menuFoodName.text = menuItemsName[position].foodName
                menuPrice.text = menuItemsName[position].price.toString()
                Glide.with(menuImage.context).load(menuItemsName[position].image).into(menuImage)
            }
        }
    }
    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    fun getBitmapFromURL(src: String?): Bitmap? {

        return try {
            Log.e("src", src!!)
            val url = URL(src)
            val connection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input = connection.inputStream
            val myBitmap = BitmapFactory.decodeStream(input)
            Log.e("Bitmap", "returned")
            myBitmap
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("Exception", e.message!!)
            null
        }
    }
}



