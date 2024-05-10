package com.example.wavesoffood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.wavesoffood.databinding.ActivityMainBinding
import com.example.wavesoffood.ui.theme.WavesOfFoodTheme
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate((layoutInflater))
        setContentView(binding.root)
        var NavController = findNavController(R.id.fragmentContainerView)
        var bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setupWithNavController(NavController)
        binding.notificationButton.setOnClickListener {
            val bottomSheetDialog = Notifaction_Bottom_Fragment()
            bottomSheetDialog.show(supportFragmentManager, "Test")
        }
    }
}
