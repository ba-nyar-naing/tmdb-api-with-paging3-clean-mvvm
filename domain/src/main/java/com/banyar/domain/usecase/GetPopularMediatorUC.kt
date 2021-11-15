package com.banyar.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.paging.BaseRemoteMediator
import com.banyar.domain.repository.CachedMoviesRepository
import com.banyar.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

interface GetPopularMediatorUC : BaseUseCase<Any, Flow<PagingData<MovieDetails>>>

class GetPopularMediatorUCImpl(
    private val moviesRepository: MoviesRepository,
    private val cachedMoviesRepository: CachedMoviesRepository,
) : GetPopularMediatorUC {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun invoke(params: Any): Flow<PagingData<MovieDetails>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = PopularMoviesMediatorPagingSource(
                cachedMoviesRepository,
                moviesRepository
            ),
            pagingSourceFactory = {
                // FIXME: 11/15/21 avoid accessing database on the main thread
                cachedMoviesRepository.pagingSource()
            }
        ).flow
    }

}

class PopularMoviesMediatorPagingSource(
    cachedMoviesRepository: CachedMoviesRepository,
    moviesRepository: MoviesRepository,
) : BaseRemoteMediator(
    "",
    cachedMoviesRepository,
    moviesRepository
)