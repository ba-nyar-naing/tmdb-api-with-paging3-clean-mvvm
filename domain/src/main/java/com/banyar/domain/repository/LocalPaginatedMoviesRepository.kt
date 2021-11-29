package com.banyar.domain.repository

import androidx.paging.PagingSource
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.Result
import com.banyar.domain.paging.MovieSourceType
import kotlinx.coroutines.flow.Flow

interface LocalPaginatedMoviesRepository {

    fun insertAll(movies: List<MovieDetails>): Flow<Unit>

    fun clearAll(movieSourceType: MovieSourceType): Flow<Unit>

    fun pagingSource(movieSourceType: MovieSourceType): PagingSource<Int, MovieDetails>

    fun pagingSourceFavourite(): PagingSource<Int, MovieDetails>

    fun getFavourite(id: Int): Flow<MovieDetails?>

    fun insertFavourite(movie: MovieDetails): Flow<Result>

    fun deleteFavourite(id: Int): Flow<Result>
}