package com.banyar.presentation.ui.listing

import android.os.Bundle
import androidx.activity.viewModels
import com.banyar.presentation.ui.base.BaseActivity
import com.banyar.presentation.databinding.ActivityListingBinding
import timber.log.Timber

class ListingActivity : BaseActivity() {

    private lateinit var binding: ActivityListingBinding

    private val viewModel: ListingVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserver()
        setupUIElements()
        setupActionListener()
    }

    override fun setupUIElements() {
        Timber.d("setupUIElements: Not yet implemented")
    }

    override fun setupObserver() {
        Timber.d("setupObserver: Not yet implemented")
    }

    override fun setupActionListener() {
        binding.btnDoAction.setOnClickListener {
            viewModel.getPopularMovies()
        }
    }
}