package com.banyar.domain.repository

import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.PopularMovies
import kotlinx.coroutines.flow.Flow

interface RemoteMoviesRepository {

    fun getDetails(id: Int): Flow<MovieDetails>

    fun getPopularMovies(page: Int): Flow<PopularMovies>

    fun getUpcomingMovies(page: Int): Flow<PopularMovies>

    fun getTopRatedMovies(page: Int): Flow<PopularMovies>

    fun getNowPlayingMovies(page: Int): Flow<PopularMovies>
}