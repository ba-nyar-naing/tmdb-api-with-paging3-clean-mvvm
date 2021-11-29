package com.banyar.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.banyar.presentation.databinding.FragmentHomeBinding
import com.banyar.presentation.ui.adapter.CategoryAdapter
import com.banyar.presentation.ui.adapter.LoadingStateAdapter
import com.banyar.presentation.ui.base.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeVM by viewModels()

    private var categoryAdapter: CategoryAdapter? = null

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
        setupCategoryAdapter()
    }

    override fun setupObserver() {
    }

    override fun setupActionListener() {

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