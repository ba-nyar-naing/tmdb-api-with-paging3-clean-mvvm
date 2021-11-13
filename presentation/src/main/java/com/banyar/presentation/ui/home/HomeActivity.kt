package com.banyar.presentation.ui.home

import android.os.Bundle
import com.banyar.presentation.databinding.ActivityHomeBinding
import com.banyar.presentation.ui.base.BaseActivity

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}