package com.example.wavesoffood.adapter


import android.app.AlertDialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wavesoffood.Models.FoodInfo
import com.example.wavesoffood.adapter.CartAdapter.*
import com.example.wavesoffood.databinding.CartItemBinding
import com.example.wavesoffood.interfaces.IClickListener

class CartAdapter(
    private val cartItems: MutableList<FoodInfo>,
    private val listener: IClickListener,
    private val context: Context
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
                cartItemPrice.text = cartItems[position].newPrice.toString()
                Glide.with(cartImage.context).load(cartItems[position].imageMenu).into(cartImage)
                binding.cartItemQuantity.setText(cartItems[position].quantity.toString())
                cartFoodQuantity.text = cartItems[position].foodQuantity

                minusButton.setOnClickListener {
                    decreaseQuantity((position))
                }
                plusButton.setOnClickListener {
                    increaseQuantity((position))
                }
                cartItemQuantity.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        // This will be triggered when text is changed
                        onTextChanged(position, s)
                    }

                    override fun afterTextChanged(s: Editable?) {}
                })
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
            binding.cartItemQuantity.setText(cartItems[position].quantity.toString())
            binding.cartItemPrice.text = cartItems[position].newPrice.toString()
            listener.onClickListener(cartItems.sumOf { it.newPrice })
            listener.updateFoodInfoListener(cartItems[position].id, cartItems[position].quantity)
            calculateTotalPrice()
        }
        private fun onTextChanged(position: Int, newText: CharSequence?) {
            val newQuantity = newText?.toString()?.toIntOrNull() ?: 0
            val maxQuantity = cartItems[position].foodQuantity.toIntOrNull() ?: 0
            if (newQuantity > maxQuantity) {
                showMaxQuantityDialog()
                binding.cartItemQuantity.setText(maxQuantity.toString())
            } else if (newQuantity != cartItems[position].quantity) {
                cartItems[position].quantity = newQuantity
                binding.cartItemPrice.text = cartItems[position].newPrice.toString()
                listener.updateFoodInfoListener(cartItems[position].id, newQuantity)
                listener.onClickListener(cartItems.sumOf { it.newPrice })
                calculateTotalPrice()
            }
        }
        private fun showMaxQuantityDialog() {
            AlertDialog.Builder(context)
                .setTitle("Quantity Exceeded")
                .setMessage("The quantity cannot exceed the available.")
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }

        private fun decreaseQuantity(position: Int) {
            if (cartItems[position].quantity > 1) {
                cartItems[position].quantity--
                binding.cartItemQuantity.setText(cartItems[position].quantity.toString())
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


