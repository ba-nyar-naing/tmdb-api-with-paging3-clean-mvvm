package com.banyar.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.banyar.domain.model.Favourite
import com.banyar.presentation.R
import com.banyar.presentation.databinding.ItemMovieBinding

class FavouriteAdapter : PagingDataAdapter<Favourite, FavouriteAdapter.ViewHolder>(DataDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { movie -> holder.bind(movie) }
    }

    inner class ViewHolder(
        private val binding: ItemMovieBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Favourite) {
            with(binding) {
                txvTitle.text = movie.title
                ivPoster.load("https://image.tmdb.org/t/p/w500${movie.posterPath}")

                val context = root.context
                root.rootView.setOnClickListener {
                    val bundle = bundleOf(
                        context.getString(R.string.id) to movie.id
                    )
                    root.findNavController().navigate(R.id.desc_detail, bundle)
                }
            }
        }
    }

    object DataDiffer : DiffUtil.ItemCallback<Favourite>() {
        override fun areItemsTheSame(
            oldItem: Favourite,
            newItem: Favourite
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Favourite,
            newItem: Favourite
        ): Boolean {
            return oldItem == newItem
        }
    }
}