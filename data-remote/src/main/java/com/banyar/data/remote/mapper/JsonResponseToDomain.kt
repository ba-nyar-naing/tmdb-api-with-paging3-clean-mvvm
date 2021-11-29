package com.banyar.data.remote.mapper

import com.banyar.data.remote.model.GenreResponse
import com.banyar.data.remote.model.MovieDetailsResponse
import com.banyar.data.remote.model.MovieListResponse
import com.banyar.domain.model.Genre
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.PopularMovies

internal fun GenreResponse.toDomain(): Genre {
    return Genre(id, name)
}

internal fun MovieDetailsResponse.toDomain(): MovieDetails {
    return MovieDetails(
        adult,
        backdropPath,
        budget,
        genres?.map { genre -> genre.toDomain() },
        homepage,
        id,
        imdbId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        revenue,
        runtime,
        status,
        tagline,
        title,
        video,
        voteAverage,
        voteCount
    )
}

internal fun MovieListResponse.toDomain(): PopularMovies {
    return PopularMovies(
        page,
        results.map { movie -> movie.toDomain() },
        totalPages,
        totalResults
    )
}