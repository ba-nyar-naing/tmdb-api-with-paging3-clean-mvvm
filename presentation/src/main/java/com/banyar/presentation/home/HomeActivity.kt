package com.banyar.presentation.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.banyar.presentation.base.BaseActivity
import com.banyar.presentation.databinding.ActivityHomeBinding
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
    }
}