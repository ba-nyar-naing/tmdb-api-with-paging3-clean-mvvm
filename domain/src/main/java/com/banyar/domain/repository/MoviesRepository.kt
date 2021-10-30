package com.banyar.domain.repository

import com.banyar.domain.model.Movie
import com.banyar.domain.model.PopularMovies
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

interface MoviesRepository {

    fun getPopular(page: Int): Flow<List<Movie>>

    fun getPopularMovies(page:Int) : Flow<PopularMovies>
}