package com.banyar.data.remote.repository

import com.banyar.data.remote.api.TMDBApiService
import com.banyar.data.remote.mapper.toDomain
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.PopularMovies
import com.banyar.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteMoviesRepositoryImpl @Inject constructor(
    private val tmdbService: TMDBApiService
) : RemoteMoviesRepository {

    override fun getDetails(id: Int): Flow<MovieDetails> = flow {
        val response = tmdbService.getDetails(id)
        val movieDetails = response.toDomain()
        emit(movieDetails)
    }

    override fun getPopularMovies(page: Int): Flow<PopularMovies> = flow {
        val response = tmdbService.getPopular(page)
        val popularMovies = response.toDomain()
        emit(popularMovies)
    }

    override fun getUpcomingMovies(page: Int): Flow<PopularMovies> = flow {
        val response = tmdbService.getUpcoming(page)
        val popularMovies = response.toDomain()
        emit(popularMovies)
    }
}