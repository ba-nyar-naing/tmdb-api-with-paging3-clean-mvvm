package com.banyar.data.local.repository

import com.banyar.data.local.dao.PopularMovieDAO
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.repository.LocalPopularMoviesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalPopularMoviesRepositoryImpl @Inject constructor(
    private val popularMovieDAO: PopularMovieDAO,
) : LocalPopularMoviesRepository {

    override fun insertAll(movieDetails: List<MovieDetails>) = flow {
        movieDetails.map { popularMovieDAO.insert(it) }
        emit(Unit)
    }

    override fun clearAll() = flow {
        emit(popularMovieDAO.clearAll())
    }

    override fun pagingSource() = popularMovieDAO.pagingSource()
}