package com.example.wavesoffood.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.wavesoffood.History_Details_Activity
import com.example.wavesoffood.Models.CartInfo
import com.example.wavesoffood.Models.UpdateStatusCartInfoRequest
import com.example.wavesoffood.R
import com.example.wavesoffood.databinding.BuyAgainItemBinding
import com.example.wavesoffood.services.ApiClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuyAgainAdapter(
    private val historyItems: List<CartInfo>,
    private val requireContext: Context
) : RecyclerView.Adapter<BuyAgainAdapter.BuyAgainViewHolder>() {
    private val itemClickListener: MenuAdapter.OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyAgainViewHolder {
        val binding =
            BuyAgainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyAgainViewHolder(binding)
    }

    override fun getItemCount(): Int = historyItems.size

    override fun onBindViewHolder(holder: BuyAgainViewHolder, position: Int) {
        val cartInfo = historyItems[position]
        holder.bind(cartInfo)
        if (cartInfo.status == "Delivery"){
            holder.completedOrderButton.visibility = View.VISIBLE
        }else{
            holder.completedOrderButton.visibility = View.GONE
        }
    }

    inner class BuyAgainViewHolder(private val binding: BuyAgainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val moreDetailsButton: Button = itemView.findViewById(R.id.moreDetailsButton)
        val completedOrderButton: Button = itemView.findViewById(R.id.completedOrderButton)
        fun bind(cartInfo: CartInfo) {
            binding.buyAgainStatus.text = cartInfo.status
            binding.buyAgainDateOrder.text = cartInfo.dateOrder
            binding.buyAgainTotalPrice.text = cartInfo.totalPrice.toString()
        }

        init {
            moreDetailsButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(position)
                }

                // set on click listener to open details
                val intent = Intent(requireContext, History_Details_Activity::class.java)
                intent.putExtra("foodStatus", historyItems[position].status)
                intent.putExtra("foodDateOrder", historyItems[position].dateOrder)
                intent.putExtra("foodTotalPrice", historyItems[position].totalPrice)
                val gson = Gson()
                val json = gson.toJson(historyItems[position].cartDetails);
                intent.putExtra("cartDetails", json)

                requireContext.startActivity(intent)
            }

            completedOrderButton.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener?.onItemClick(position)
                }
                val cartInfo = historyItems[position]
                val updateStatus = UpdateStatusCartInfoRequest()
                updateStatus.cartInfoId = cartInfo.id
                updateStatus.status = "Completed"
                val call = ApiClient.apiService.updateStatusCartInfo(updateStatus)
                call.enqueue(object : Callback<Boolean?> {
                    override fun onResponse(
                        call: Call<Boolean?>,
                        response: Response<Boolean?>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(requireContext, "Completed Order", Toast.LENGTH_SHORT).show()
                        } else {
                            Log.d(
                                "CompletedOrder",
                                "Post failed: ${response.message()} ${response.errorBody()?.string()}"
                            )
                        }
                    }

                    override fun onFailure(call: Call<Boolean?>, t: Throwable) {
                        Log.d("CompletedOrderFail", t.toString())
                    }
                })
            }
        }
    }
}