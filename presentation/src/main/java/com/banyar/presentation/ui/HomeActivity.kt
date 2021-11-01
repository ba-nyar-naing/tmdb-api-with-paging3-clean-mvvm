package com.banyar.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.banyar.presentation.R
import com.banyar.presentation.databinding.ActivityHomeBinding
import com.banyar.presentation.ui.base.BaseActivity

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUIElements()
    }

    private fun setupUIElements() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)
    }

    fun setBottomNavBarVisibility(visible: Boolean) {
        binding.navView.visibility = if (visible) View.VISIBLE else View.GONE
    }
}