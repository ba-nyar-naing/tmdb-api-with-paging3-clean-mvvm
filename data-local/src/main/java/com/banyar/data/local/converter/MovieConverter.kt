package com.banyar.data.local.converter

import androidx.room.TypeConverter
import com.banyar.data.local.model.PaginatedMovieEntity
import com.banyar.domain.model.MovieDetails

class MovieConverter {

    @TypeConverter
    fun toEntity(movie: MovieDetails): PaginatedMovieEntity {
        return PaginatedMovieEntity(
            movie.title!!,
            movie.posterPath!!,
            movie.id!!,
            movie.category!!,
            movie.pagingId!!
        )
    }

    @TypeConverter
    fun fromEntity(movie: PaginatedMovieEntity): MovieDetails {
        return MovieDetails(id = movie.movieId, title = movie.title, posterPath = movie.posterPath)
    }
}