package com.banyar.presentation.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.banyar.presentation.ui.base.BaseActivity
import com.banyar.presentation.databinding.ActivityDetailBinding
import timber.log.Timber

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val viewModel: DetailVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserver()
        setupUIElements()
        setupActionListener()
    }

    override fun setupUIElements() {
        Timber.d("setupUIElements: Not yet implemented")
        viewModel.getMovieDetail()
    }

    override fun setupObserver() {
        Timber.d("setupObserver: Not yet implemented")
    }

    override fun setupActionListener() {
        Timber.d("setupActionListener: Not yet implemented")
    }
}