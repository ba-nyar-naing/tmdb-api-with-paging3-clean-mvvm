package com.banyar.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.banyar.domain.model.MovieDetails
import com.banyar.presentation.R
import com.banyar.presentation.databinding.ItemMovieBinding

class MovieAdapter : PagingDataAdapter<MovieDetails, MovieAdapter.ViewHolder>(DataDiffer) {

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

        fun bind(movie: MovieDetails) {
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

    object DataDiffer : DiffUtil.ItemCallback<MovieDetails>() {
        override fun areItemsTheSame(
            oldItem: MovieDetails,
            newItem: MovieDetails
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieDetails,
            newItem: MovieDetails
        ): Boolean {
            return oldItem == newItem
        }
    }
}