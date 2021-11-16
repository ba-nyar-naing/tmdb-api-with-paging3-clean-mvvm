package com.banyar.domain.repository

import androidx.paging.PagingSource
import com.banyar.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface LocalPopularMoviesRepository {

    fun insertAll(movies: List<MovieDetails>): Flow<Unit>

    fun clearAll(): Flow<Unit>

    fun pagingSource(): PagingSource<Int, MovieDetails>
}