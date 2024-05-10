package com.example.wavesoffood.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.adapter.CartAdapter.*
import com.example.wavesoffood.databinding.CartItemBinding
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class CartAdapter (private val cartItems: List<FoodInfo>) : RecyclerView.Adapter<CartViewHolder>() {

    private var itemQuantities = IntArray(cartItems.size){1}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class CartViewHolder (private val binding : CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                cartFoodName.text = cartItems[position].foodName
                cartItemPrice.text = cartItems[position].price.toString()
                Glide.with(cartImage.context).load(cartItems[position].image).into(cartImage)
                //cartImage.setImageBitmap(image)
                cartItemQuantity.text = quantity.toString()

                minusButton.setOnClickListener {
                    decreaseQuantity((position))
                }
                plusButton.setOnClickListener {
                    increaseQuantity((position))
                }
                deleteButton.setOnClickListener {
                    val itemPosition = adapterPosition
                    if ( itemPosition != RecyclerView.NO_POSITION) {
                        deleteItem(itemPosition)
                    }
                }
            }
        }

        private fun increaseQuantity (position : Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                binding.cartItemQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun decreaseQuantity (position : Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.cartItemQuantity.text = itemQuantities[position].toString()
            }
        }

        private fun deleteItem (position : Int) {
            //cartItems.removeAt(position)
//            CartItemPrice.removeAt(position)
//            CartImage.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)
        }
    }
    private fun addItem(foodName: String, itemPrice: String, imageResId: Int) {
       // cartItems.add(foodName)
//        CartItemPrice.add(itemPrice)
//        CartImage.add(imageResId)
        itemQuantities += 1
        notifyItemInserted(cartItems.size - 1)
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