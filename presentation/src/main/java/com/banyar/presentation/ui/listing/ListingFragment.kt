package com.banyar.presentation.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.banyar.domain.paging.MovieSourceType
import com.banyar.presentation.databinding.FragmentListingBinding
import com.banyar.presentation.ui.adapter.LoadingStateAdapter
import com.banyar.presentation.ui.adapter.MoviesAdapter
import com.banyar.presentation.ui.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListingFragment : BaseFragment() {

    private var _binding: FragmentListingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListingVM by viewModels()

    private var moviesAdapter: MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun setupUIElements() {
        setActionBarTitle("Popular movies")

        val sourceType = requireActivity().intent.getSerializableExtra("Listing") as MovieSourceType

        moviesAdapter = MoviesAdapter()

        binding.rcvMovie.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = moviesAdapter?.withLoadStateFooter(
                footer = LoadingStateAdapter()
            )
        }

        when (sourceType) {
            MovieSourceType.POPULAR -> {
                lifecycleScope.launch {
                    viewModel.getPopularPagingData().collectLatest { last ->
                        moviesAdapter?.submitData(last)
                    }
                }
            }
            MovieSourceType.UPCOMING -> {
                lifecycleScope.launch {
                    viewModel.getUpcomingPagingData().collectLatest { last ->
                        moviesAdapter?.submitData(last)
                    }
                }
            }
        }
    }

    override fun setupObserver() {
    }

    override fun setupActionListener() {
    }

}