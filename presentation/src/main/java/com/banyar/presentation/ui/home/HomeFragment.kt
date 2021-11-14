package com.banyar.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.banyar.domain.paging.MovieSourceType
import com.banyar.presentation.R
import com.banyar.presentation.databinding.FragmentHomeBinding
import com.banyar.presentation.ui.adapter.FavouritesAdapter
import com.banyar.presentation.ui.adapter.LoadingStateAdapter
import com.banyar.presentation.ui.adapter.MoviesAdapter
import com.banyar.presentation.ui.base.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeVM by viewModels()

    private var popularAdapter: MoviesAdapter? = null
    private var upcomingAdapter: MoviesAdapter? = null
    private var favouriteAdapter: FavouritesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
//        setupFavouriteAdapter()
    }

    override fun setupUIElements() {
        setActionBarTitle("Home")

        setupPopularAdapter()
//        setupUpcomingAdapter()
    }

    override fun setupObserver() {
    }

    override fun setupActionListener() {
        binding.btnMorePopular.setOnClickListener {
            val bundle = bundleOf(
                requireContext().getString(R.string.listing) to MovieSourceType.POPULAR
            )
            findNavController().navigate(R.id.desc_listing, bundle)
        }
        binding.btnMoreUpcoming.setOnClickListener {
            val bundle = bundleOf(
                requireContext().getString(R.string.listing) to MovieSourceType.UPCOMING
            )
            findNavController().navigate(R.id.desc_listing, bundle)
        }
        binding.btnMoreFavourite.setOnClickListener {
            val bundle = bundleOf(
                requireContext().getString(R.string.listing) to MovieSourceType.UPCOMING
            )
            findNavController().navigate(R.id.desc_listing, bundle)
        }
    }

    private fun setupPopularAdapter() {
        popularAdapter = MoviesAdapter()

        binding.rcvPopular.apply {
            adapter = popularAdapter?.withLoadStateFooter(
                footer = LoadingStateAdapter()
            )
        }

        lifecycleScope.launch {
            viewModel.getPopularPagingData().collect { last ->
                popularAdapter?.submitData(last)
            }
        }
    }

    private fun setupUpcomingAdapter() {
        upcomingAdapter = MoviesAdapter()

        binding.rcvUpcoming.apply {
            adapter = upcomingAdapter?.withLoadStateFooter(
                footer = LoadingStateAdapter()
            )
        }

        lifecycleScope.launch {
            viewModel.getUpcomingPagingData().collect { last ->
                upcomingAdapter?.submitData(last)
            }
        }
    }

    private fun setupFavouriteAdapter() {
        favouriteAdapter = FavouritesAdapter()

        binding.rcvFavourite.apply {
            adapter = favouriteAdapter?.withLoadStateFooter(
                footer = LoadingStateAdapter()
            )
        }

        lifecycleScope.launch {
            viewModel.getFavouritePagingData().collect { last ->
                favouriteAdapter?.submitData(last)
            }
        }
    }
}