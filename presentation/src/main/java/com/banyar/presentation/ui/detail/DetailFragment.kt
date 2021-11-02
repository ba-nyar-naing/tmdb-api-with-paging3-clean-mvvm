package com.banyar.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.banyar.domain.model.Genre
import com.banyar.presentation.R
import com.banyar.presentation.databinding.FragmentDetailBinding
import com.banyar.presentation.ui.adapter.MoviesAdapter
import com.banyar.presentation.ui.base.BaseFragment
import com.google.android.material.chip.Chip

class DetailFragment : BaseFragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailVM by viewModels()

    private var moviesAdapter: MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun setupUIElements() {
        setBottomNavBarVisibility(false)

        val title = arguments?.getString(getString(R.string.title))
        setActionBarTitle("$title movies")

        val movieId = arguments?.getInt(getString(R.string.id)) ?: -1
        viewModel.getMovieDetails(movieId)
    }

    override fun setupObserver() {
        viewModel.movieDetails.observe(this) { movieDetails ->
            setActionBarTitle(movieDetails.title)

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

    override fun onDestroy() {
        super.onDestroy()
        setBottomNavBarVisibility(true)
    }

    private fun setupGenreChip(genres: List<Genre>) {
        genres.map { genre ->
            val chip = Chip(requireContext())
            chip.isClickable = false
            chip.text = genre.name
            binding.cgGenre.addView(chip as View)
        }
    }
}
