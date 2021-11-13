package com.banyar.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.load
import com.banyar.domain.model.Genre
import com.banyar.presentation.R
import com.banyar.presentation.databinding.FragmentDetailBinding
import com.banyar.presentation.ui.base.BaseFragment
import com.google.android.material.chip.Chip

class DetailFragment : BaseFragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailVM by viewModels()

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
        viewModel.checkFavouriteStats(movieId)
    }

    override fun setupObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is DetailUIState.FavouriteDeleteFailed -> {
                    Toast.makeText(requireContext(), "Failed to remove", Toast.LENGTH_SHORT).show()
                }
                is DetailUIState.ShowSaveFavourite -> {
                    binding.btnFavourite.setText(R.string.favourite)
                }
                is DetailUIState.FavouriteSaveFailed -> {
                    Toast.makeText(requireContext(), "Failed to save", Toast.LENGTH_SHORT).show()
                }
                is DetailUIState.ShowDeleteFavourite -> {
                    binding.btnFavourite.setText(R.string.saved)
                }
            }
        }

        viewModel.movieDetails.observe(viewLifecycleOwner) { movieDetails ->
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
        with(binding) {
            btnFavourite.setOnClickListener {
                when (btnFavourite.text) {
                    requireContext().getString(R.string.favourite) -> viewModel.insertFavourite()
                    requireContext().getString(R.string.saved) -> viewModel.deleteFavourite()
                }
            }
        }
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
