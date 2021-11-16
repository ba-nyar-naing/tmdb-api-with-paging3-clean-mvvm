package com.banyar.data.local.repository

import com.banyar.data.local.dao.PaginatedMovieDAO
import com.banyar.data.local.mapper.toEntity
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.paging.MovieSourceType
import com.banyar.domain.repository.LocalPaginatedMoviesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PaginatedMoviesRepositoryImpl @Inject constructor(
    private val paginatedMovieDAO: PaginatedMovieDAO,
) : LocalPaginatedMoviesRepository {

    override fun insertAll(movieSourceType: MovieSourceType, movies: List<MovieDetails>) = flow {
        movies.map { paginatedMovieDAO.insert(it.toEntity()) }
        emit(Unit)
    }

    override fun clearAll(movieSourceType: MovieSourceType) = flow {
        emit(paginatedMovieDAO.deleteAll(movieSourceType.toString()))
    }

    override fun pagingSource(movieSourceType: MovieSourceType) =
        paginatedMovieDAO.pagingSource(movieSourceType.toString())
}