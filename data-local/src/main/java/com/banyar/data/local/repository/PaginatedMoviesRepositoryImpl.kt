package com.banyar.data.local.repository

import androidx.paging.PagingSource
import com.banyar.data.local.dao.PaginatedMovieDAO
import com.banyar.data.local.mapper.toEntity
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.Result
import com.banyar.domain.paging.MovieSourceType
import com.banyar.domain.repository.LocalPaginatedMoviesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PaginatedMoviesRepositoryImpl @Inject constructor(
    private val paginatedMovieDAO: PaginatedMovieDAO,
) : LocalPaginatedMoviesRepository {

    override fun insertAll(movies: List<MovieDetails>) = flow {
        movies.map { paginatedMovieDAO.insert(it.toEntity()) }
        emit(Unit)
    }

    override fun clearAll(movieSourceType: MovieSourceType) = flow {
        emit(paginatedMovieDAO.deleteAll(movieSourceType.toString()))
    }

    override fun pagingSource(movieSourceType: MovieSourceType) =
        paginatedMovieDAO.pagingSource(movieSourceType.toString())

    override fun pagingSourceFavourite(): PagingSource<Int, MovieDetails> =
        paginatedMovieDAO.pagingSourceFavourite()

    override fun insertFavourite(movie: MovieDetails) = flow {
        val result = paginatedMovieDAO.insert(movie.toEntity())
        if (result > 0) emit(Result.SUCCESS)
        else emit(Result.FAILURE)
    }

    override fun deleteFavourite(id: Int) = flow {
        val result = paginatedMovieDAO.delete(id, MovieSourceType.FAVOURITE.toString())
        if (result > 0) emit(Result.SUCCESS)
        else emit(Result.FAILURE)
    }

    override fun getFavourite(id: Int) = flow {
        val result = paginatedMovieDAO.get(id, MovieSourceType.FAVOURITE.toString())
        emit(result)
    }
}