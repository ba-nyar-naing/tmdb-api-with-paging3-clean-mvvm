package com.banyar.data.local.repository

import com.banyar.data.local.dao.PopularMovieDAO
import com.banyar.data.local.mapper.toEntity
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.repository.LocalPopularMoviesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val popularMovieDAO: PopularMovieDAO,
) : LocalPopularMoviesRepository {

    override fun insertAll(movies: List<MovieDetails>) = flow {
        movies.map { popularMovieDAO.insert(it.toEntity()) }
        emit(Unit)
    }

    override fun clearAll() = flow {
        emit(popularMovieDAO.deleteAll())
    }

    override fun pagingSource() = popularMovieDAO.pagingSource()
}