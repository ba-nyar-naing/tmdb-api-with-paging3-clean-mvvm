package com.banyar.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.paging.BaseMoviesPagingSource
import com.banyar.domain.paging.MovieSourceType
import com.banyar.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

interface GetPopularUC : BaseUseCase<Any, Flow<PagingData<MovieDetails>>>

class GetPopularUCImpl(
    private val repository: MoviesRepository
) : GetPopularUC {

    override suspend fun invoke(params: Any) =
        Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = {
                PopularMoviesPagingSource(repository)
            }
        ).flow
}

class PopularMoviesPagingSource(
    repository: MoviesRepository
) : BaseMoviesPagingSource(
    MovieSourceType.POPULAR,
    repository
)