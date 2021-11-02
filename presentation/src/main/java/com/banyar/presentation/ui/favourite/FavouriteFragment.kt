package com.banyar.presentation.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.banyar.presentation.databinding.FragmentFavouriteBinding
import com.banyar.presentation.ui.adapter.LoadingStateAdapter
import com.banyar.presentation.ui.adapter.MoviesAdapter
import com.banyar.presentation.ui.base.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavouriteFragment : BaseFragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavouriteVM by viewModels()

    private var moviesAdapter: MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun setupUIElements() {
        setActionBarTitle("Favourite movies")

        moviesAdapter = MoviesAdapter()

        binding.rcvMovie.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
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