package com.banyar.data.local.converter

import androidx.room.TypeConverter
import com.banyar.data.local.model.PopularMovieEntity
import com.banyar.domain.model.MovieDetails

class MovieConverter {

    @TypeConverter
    fun toEntity(movie: MovieDetails): PopularMovieEntity {
        return PopularMovieEntity(movie.title!!, movie.posterPath!!, movie.id!!, movie.pagingId!!)
    }

    @TypeConverter
    fun fromEntity(movie: PopularMovieEntity): MovieDetails {
        return MovieDetails(id = movie.movieId, title = movie.title, posterPath = movie.posterPath)
    }
}