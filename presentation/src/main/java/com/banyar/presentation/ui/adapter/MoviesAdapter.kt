package com.banyar.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.banyar.domain.model.Movie
import com.banyar.presentation.databinding.ItemImageMovieBinding

class MoviesAdapter : PagingDataAdapter<Movie, MoviesAdapter.ViewHolder>(DataDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImageMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { movie -> holder.bind(movie) }
    }

    inner class ViewHolder(
        private val binding: ItemImageMovieBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                txvTitle.text = movie.title
                ivPoster.load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            }
        }
    }

    object DataDiffer : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }
    }
}