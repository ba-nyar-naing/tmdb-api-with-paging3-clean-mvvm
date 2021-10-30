package com.banyar.presentation.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import coil.load
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
        val movieId = intent.getIntExtra("id", -1)
        viewModel.getMovieDetails(movieId)
    }

    override fun setupObserver() {
        viewModel.movieDetails.observe(this) { movieDetails ->
            title = movieDetails.title

            with(binding) {
                txvOverview.text = movieDetails.overview
                ivPoster.load("https://image.tmdb.org/t/p/w500${movieDetails.posterPath}")
            }
        }
    }

    override fun setupActionListener() {
        Timber.d("setupActionListener: Not yet implemented")
    }
}