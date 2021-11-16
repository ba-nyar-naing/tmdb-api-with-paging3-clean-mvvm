package com.banyar.domain.repository

import androidx.paging.PagingSource
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.paging.MovieSourceType
import kotlinx.coroutines.flow.Flow

interface LocalPaginatedMoviesRepository {

    fun insertAll(movieSourceType: MovieSourceType, movies: List<MovieDetails>): Flow<Unit>

    fun clearAll(movieSourceType: MovieSourceType): Flow<Unit>

    fun pagingSource(movieSourceType: MovieSourceType): PagingSource<Int, MovieDetails>
}