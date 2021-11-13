package com.banyar.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.paging.BaseMoviesPagingSource
import com.banyar.domain.paging.MovieSourceType
import com.banyar.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

interface GetUpcomingUC : BaseUseCase<Any, Flow<PagingData<MovieDetails>>>

class GetUpcomingUCImpl(
    private val repository: MoviesRepository
) : GetUpcomingUC {

    override suspend fun invoke(params: Any) =
        Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = {
                UpcomingMoviesPagingSource(repository)
            }
        ).flow
}

class UpcomingMoviesPagingSource(
    repository: MoviesRepository
) : BaseMoviesPagingSource(
    MovieSourceType.UPCOMING,
    repository
) 