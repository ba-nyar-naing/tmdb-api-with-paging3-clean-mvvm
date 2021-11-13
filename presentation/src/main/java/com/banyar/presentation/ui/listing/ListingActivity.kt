package com.banyar.presentation.ui.listing

import android.os.Bundle
import com.banyar.presentation.databinding.ActivityListingBinding
import com.banyar.presentation.ui.base.BaseActivity

class ListingActivity : BaseActivity() {

    private lateinit var binding: ActivityListingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}