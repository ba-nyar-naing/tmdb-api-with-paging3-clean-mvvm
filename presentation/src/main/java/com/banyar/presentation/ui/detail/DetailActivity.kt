package com.banyar.presentation.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import coil.load
import com.banyar.domain.model.Genre
import com.banyar.presentation.databinding.ActivityDetailBinding
import com.banyar.presentation.ui.base.BaseActivity
import com.google.android.material.chip.Chip

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
                txvTitle.text = movieDetails.title
                txvOverview.text = movieDetails.overview
                ivPoster.load("https://image.tmdb.org/t/p/w500${movieDetails.posterPath}")

                movieDetails.genres?.let { setupGenreChip(it) }
            }
        }
    }

    override fun setupActionListener() {
    }

    private fun setupGenreChip(genres: List<Genre>) {
        genres.map { genre ->
            val chip = Chip(this)
            chip.isClickable = false
            chip.text = genre.name
            binding.cgGenre.addView(chip as View)
        }
    }
}