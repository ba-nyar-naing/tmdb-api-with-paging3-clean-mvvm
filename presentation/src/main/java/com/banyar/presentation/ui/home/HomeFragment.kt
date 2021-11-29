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
import com.banyar.presentation.ui.adapter.CategoryAdapter
import com.banyar.presentation.ui.adapter.FavouriteAdapter
import com.banyar.presentation.ui.adapter.LoadingStateAdapter
import com.banyar.presentation.ui.adapter.MovieAdapter
import com.banyar.presentation.ui.base.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeVM by viewModels()

    private var categoryAdapter: CategoryAdapter? = null
    private var favouriteAdapter: FavouriteAdapter? = null

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
        setupFavouriteAdapter()
    }

    override fun setupUIElements() {
        setActionBarTitle("Home")

//        setupPopularAdapter()
//        setupUpcomingAdapter()
        setupCategoryAdapter()
    }

    override fun setupObserver() {
    }

    override fun setupActionListener() {
        binding.btnMoreFavourite.setOnClickListener {
            val bundle = bundleOf(
                requireContext().getString(R.string.listing) to MovieSourceType.UPCOMING
            )
            findNavController().navigate(R.id.desc_listing, bundle)
        }
    }

    private fun setupFavouriteAdapter() {
        favouriteAdapter = FavouriteAdapter()

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

    private fun setupCategoryAdapter() {
        categoryAdapter = CategoryAdapter(lifecycleScope)

        binding.rcvCategory.apply {
            adapter = categoryAdapter?.withLoadStateFooter(
                footer = LoadingStateAdapter()
            )
        }

        lifecycleScope.launch {
            viewModel.getCategoryPagingData().collect { last ->
                categoryAdapter?.submitData(last)
            }
        }
    }
}