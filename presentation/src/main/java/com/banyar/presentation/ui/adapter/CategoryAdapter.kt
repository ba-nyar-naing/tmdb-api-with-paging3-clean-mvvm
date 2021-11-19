package com.banyar.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.paging.MovieSourceType
import com.banyar.domain.usecase.BaseUseCase
import com.banyar.presentation.databinding.ItemCategoryBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CategoryAdapter(
    val lifecycleScope: LifecycleCoroutineScope
) : PagingDataAdapter<Pair<MovieSourceType, BaseUseCase<Any, Flow<PagingData<MovieDetails>>>>, CategoryAdapter.ViewHolder>(
    DataDiffer
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(
        private val binding: ItemCategoryBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Pair<MovieSourceType, BaseUseCase<Any, Flow<PagingData<MovieDetails>>>>) {
            with(binding) {
                txvTitle.text = data.first.toString()

                val movieAdapter = MovieAdapter()

                binding.rcvMovies.apply {
                    adapter = movieAdapter.withLoadStateFooter(
                        footer = LoadingStateAdapter()
                    )
                }

                lifecycleScope.launch {
                    data.second(Any()).collect {
                        movieAdapter.submitData(it)
                    }
                }
            }
        }

    }

    object DataDiffer :
        DiffUtil.ItemCallback<Pair<MovieSourceType, BaseUseCase<Any, Flow<PagingData<MovieDetails>>>>>() {

        override fun areItemsTheSame(
            oldItem: Pair<MovieSourceType, BaseUseCase<Any, Flow<PagingData<MovieDetails>>>>,
            newItem: Pair<MovieSourceType, BaseUseCase<Any, Flow<PagingData<MovieDetails>>>>
        ): Boolean {
            return oldItem.first == newItem.first
        }

        override fun areContentsTheSame(
            oldItem: Pair<MovieSourceType, BaseUseCase<Any, Flow<PagingData<MovieDetails>>>>,
            newItem: Pair<MovieSourceType, BaseUseCase<Any, Flow<PagingData<MovieDetails>>>>
        ): Boolean {
            return oldItem == newItem
        }
    }
}