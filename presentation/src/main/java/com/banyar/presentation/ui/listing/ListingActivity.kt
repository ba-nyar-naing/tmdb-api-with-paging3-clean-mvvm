package com.banyar.presentation.ui.listing

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.banyar.presentation.databinding.ActivityListingBinding
import com.banyar.presentation.ui.adapter.MoviesAdapter
import com.banyar.presentation.ui.adapter.LoadingStateAdapter
import com.banyar.presentation.ui.base.BaseActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class ListingActivity : BaseActivity() {

    private lateinit var binding: ActivityListingBinding

    private val viewModel: ListingVM by viewModels()

    private var moviesAdapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserver()
        setupUIElements()
        setupActionListener()
    }

    override fun setupUIElements() {
        moviesAdapter = MoviesAdapter()

        binding.rcvMovie.apply {
            layoutManager = GridLayoutManager(applicationContext, 3)
            adapter = moviesAdapter?.withLoadStateFooter(
                footer = LoadingStateAdapter()
            )
        }

        lifecycleScope.launch {
            viewModel.getPagingData().collect { last ->
                moviesAdapter?.submitData(last)
            }
        }
    }

    override fun setupObserver() {

    }

    override fun setupActionListener() {

    }
}