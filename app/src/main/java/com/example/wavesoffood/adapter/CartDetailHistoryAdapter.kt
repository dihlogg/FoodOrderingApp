package com.example.wavesoffood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wavesoffood.Models.CartDetailInfo
import com.example.wavesoffood.databinding.HistoryItemBinding

class CartDetailHistoryAdapter(
    private val foodHistoryItems: List<CartDetailInfo>
) : RecyclerView.Adapter<CartDetailHistoryAdapter.CartViewHistoryDetailHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHistoryDetailHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHistoryDetailHolder(binding)
    }

    override fun getItemCount(): Int = foodHistoryItems.size

    override fun onBindViewHolder(holder: CartViewHistoryDetailHolder, position: Int) {
        holder.bind(position)
    }

    inner class CartViewHistoryDetailHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                historyItemFoodName.text = foodHistoryItems[position].foodName
                historyItemPrice.text = (foodHistoryItems[position].price * foodHistoryItems[position].quantity).toString()
                historyItemQuantity.text = foodHistoryItems[position].quantity.toString()
                Glide.with(historyImage.context).load(foodHistoryItems[position].image).into(historyImage)
            }
        }
    }
}