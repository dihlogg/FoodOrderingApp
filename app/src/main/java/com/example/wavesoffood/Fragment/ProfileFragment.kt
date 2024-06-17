package com.example.wavesoffood.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wavesoffood.LoginActivity
import com.example.wavesoffood.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.logOutButton.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
        binding.editTextName.text = LoginActivity.UserInfo?.userFullName;
        binding.editTextEmail.text = LoginActivity.UserInfo?.userName
        binding.editTextPassword.text = LoginActivity.UserInfo?.userPassword
        binding.editTextAddress.text = LoginActivity.UserInfo?.userAddress
        binding.editTextPhone.text = LoginActivity.UserInfo?.userPhone

        return binding.root
    }

    companion object {
    }
}