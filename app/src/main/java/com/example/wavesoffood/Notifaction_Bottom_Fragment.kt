package com.example.wavesoffood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wavesoffood.adapter.NotificationAdapter
import com.example.wavesoffood.databinding.FragmentNotifactionBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Notifaction_Bottom_Fragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentNotifactionBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotifactionBottomBinding.inflate(layoutInflater, container, false)
        val notifications = listOf(
            "Your Order Has Been Canceled Successfully",
            "Order Has Been Taken By The Driver",
            "Congratulation Your Order Placed")
        val notificationImages = listOf(
            R.drawable.sademoji,
            R.drawable.trunk1,
            R.drawable.congratulation)
        val adapter = NotificationAdapter(
            ArrayList(notifications),
            ArrayList(notificationImages)
        )

        binding.notificationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.notificationRecyclerView.adapter = adapter

        return binding.root
    }

    companion object {
    }
}