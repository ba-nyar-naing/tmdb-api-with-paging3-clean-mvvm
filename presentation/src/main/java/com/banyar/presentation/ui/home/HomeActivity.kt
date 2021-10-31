package com.banyar.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.banyar.presentation.databinding.ActivityHomeBinding
import com.banyar.presentation.ui.base.BaseActivity
import com.banyar.presentation.ui.listing.ListingActivity
import timber.log.Timber

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
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
            Toast.makeText(applicationContext, "Doing some action", Toast.LENGTH_SHORT).show()
            viewModel.getPopularMovies()
        }

        binding.btnOpenListing.setOnClickListener {
            startActivity(Intent(this, ListingActivity::class.java))
        }
    }
}