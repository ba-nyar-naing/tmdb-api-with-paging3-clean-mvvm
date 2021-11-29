package com.banyar.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.banyar.domain.usecase.MoviesMediatorPD
import com.banyar.presentation.databinding.ItemCategoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CategoryAdapter(
    val lifecycleScope: LifecycleCoroutineScope
) : PagingDataAdapter<MoviesMediatorPD, CategoryAdapter.ViewHolder>(DataDiffer) {

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

        fun bind(data: MoviesMediatorPD) {
            with(binding) {
                txvTitle.text = data.type.toString()

                val movieAdapter = MovieAdapter()

                binding.rcvMovies.apply {
                    adapter = movieAdapter.withLoadStateFooter(
                        footer = LoadingStateAdapter()
                    )
                }

                lifecycleScope.launch {
                    data.pagingData.collect {
                        movieAdapter.submitData(it)
                    }
                }
            }
        }
    }

    object DataDiffer : DiffUtil.ItemCallback<MoviesMediatorPD>() {

        override fun areItemsTheSame(
            oldItem: MoviesMediatorPD,
            newItem: MoviesMediatorPD
        ) = oldItem.type.toString() == newItem.type.toString()

        override fun areContentsTheSame(
            oldItem: MoviesMediatorPD,
            newItem: MoviesMediatorPD
        ) = oldItem == newItem
    }
}