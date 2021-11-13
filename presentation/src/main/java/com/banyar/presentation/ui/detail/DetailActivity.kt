package com.banyar.presentation.ui.detail

import android.os.Bundle
import com.banyar.presentation.databinding.ActivityDetailBinding
import com.banyar.presentation.ui.base.BaseActivity

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}