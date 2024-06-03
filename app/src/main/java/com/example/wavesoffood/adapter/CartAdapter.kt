package com.example.wavesoffood.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.adapter.CartAdapter.*
import com.example.wavesoffood.databinding.CartItemBinding
import com.example.wavesoffood.interfaces.IClickListener

class CartAdapter(
    private val cartItems: List<FoodInfo>,
    private val listener: IClickListener
) : RecyclerView.Adapter<CartViewHolder>() {

    var totalPrice: Double = cartItems.sumOf { it.newPrice }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class CartViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                cartFoodName.text = cartItems[position].name
                cartItemPrice.text = cartItems[position].price.toString()
                Glide.with(cartImage.context).load(cartItems[position].imageMenu).into(cartImage)
                cartItemQuantity.text = cartItems[position].quantity.toString()

                minusButton.setOnClickListener {
                    decreaseQuantity((position))
                }
                plusButton.setOnClickListener {
                    increaseQuantity((position))
                }
                deleteButton.setOnClickListener {
                    val itemPosition = adapterPosition
                    if (itemPosition != RecyclerView.NO_POSITION) {
                        listener.deleteFoodInfoListener(cartItems[itemPosition].id)
                        deleteItem(itemPosition)
                        listener.onClickListener(cartItems.sumOf { it.newPrice })
                    }
                }
            }
        }

        private fun increaseQuantity(position: Int) {
            cartItems[position].quantity++
            binding.cartItemQuantity.text = cartItems[position].quantity.toString()
            binding.cartItemPrice.text = cartItems[position].newPrice.toString()
            listener.onClickListener(cartItems.sumOf { it.newPrice })
            listener.updateFoodInfoListener(cartItems[position].id, cartItems[position].quantity)
            calculateTotalPrice()
        }

        private fun decreaseQuantity(position: Int) {
            if (cartItems[position].quantity > 1) {
                cartItems[position].quantity--
                binding.cartItemQuantity.text = cartItems[position].quantity.toString()
                binding.cartItemPrice.text = cartItems[position].newPrice.toString()
                calculateTotalPrice()
                listener.onClickListener(cartItems.sumOf { it.newPrice })
                listener.updateFoodInfoListener(
                    cartItems[position].id,
                    cartItems[position].quantity
                )
            }
        }

        private fun deleteItem(position: Int) {
            (cartItems as MutableList).removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)
            calculateTotalPrice()
        }
    }

    private fun calculateTotalPrice() {
        totalPrice = cartItems.sumOf { it.newPrice }
    }
}


