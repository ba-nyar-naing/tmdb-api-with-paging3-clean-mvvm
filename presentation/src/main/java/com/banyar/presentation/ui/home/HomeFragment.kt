package com.banyar.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.banyar.presentation.databinding.FragmentHomeBinding
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

    override fun setupUIElements() {
        setActionBarTitle("Home")

        setupPopularAdapter()
        setupUpcomingAdapter()
    }

    override fun setupObserver() {
    }

    override fun setupActionListener() {
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
}