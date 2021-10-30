package com.banyar.data.remote.repository

import com.banyar.data.remote.api.TMDBApiService
import com.banyar.data.remote.mapper.toDomain
import com.banyar.domain.model.Movie
import com.banyar.domain.repository.MovieListingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieListingRepositoryImpl @Inject constructor(
    private val tmdbService: TMDBApiService
) : MovieListingRepository {

    override fun getPopular(page: Int): Flow<List<Movie>> = flow {
        val moviesResponse = tmdbService.getPopular(1)
        val movies = moviesResponse.results.map { it.toDomain() }
        emit(movies)
    }
}