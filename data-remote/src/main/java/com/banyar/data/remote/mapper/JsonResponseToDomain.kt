package com.banyar.data.remote.mapper

import com.banyar.data.remote.model.MovieResponse
import com.banyar.data.remote.model.PopularResponse
import com.banyar.domain.model.Movie
import com.banyar.domain.model.PopularMovies

internal fun MovieResponse.toDomain(): Movie {
    return Movie(
        id, title, overview, posterPath, voteCount
    )
}

internal fun PopularResponse.toDomain(): PopularMovies {
    return PopularMovies(
        page,
        results.map { movie -> movie.toDomain() },
        totalPages,
        totalResults
    )
}