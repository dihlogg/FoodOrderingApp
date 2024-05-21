package com.example.wavesoffood.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.wavesoffood.History_Details_Activity
import com.example.wavesoffood.Models.CartInfo
import com.example.wavesoffood.R
import com.example.wavesoffood.databinding.BuyAgainItemBinding


class BuyAgainAdapter (
    private val historyItems: List<CartInfo>) : RecyclerView.Adapter<BuyAgainAdapter.BuyAgainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAgainViewHolder {
        val binding = BuyAgainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val myButton = binding.root.findViewById<Button>(R.id.moreDetailsButton)
        myButton.setOnClickListener {
            Log.d("moredetail", "moredetail")
            val intent = Intent(parent.context, History_Details_Activity::class.java)
            parent.context.startActivity(intent)
        }
        return BuyAgainViewHolder(binding)
    }

    override fun getItemCount(): Int = historyItems.size

    override fun onBindViewHolder(holder: BuyAgainViewHolder, position: Int) {
        holder.bind(historyItems[position])
    }

    inner class BuyAgainViewHolder (private val binding : BuyAgainItemBinding) : RecyclerView.ViewHolder (binding.root) {
        fun bind(cartInfo: CartInfo){
            binding.buyAgainStatus.text = cartInfo.status
            binding.buyAgainDateOrder.text = cartInfo.dateOrder
            binding.buyAgainTotalPrice.text = cartInfo.totalPrice.toString()

        }
    }
}