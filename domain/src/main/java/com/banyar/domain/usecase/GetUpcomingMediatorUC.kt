package com.banyar.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.paging.BaseRemoteMediator
import com.banyar.domain.paging.MovieSourceType
import com.banyar.domain.repository.LocalPaginatedMoviesRepository
import com.banyar.domain.repository.LocalRemoteKeyRepository
import com.banyar.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.flow.Flow

interface GetUpcomingMediatorUC : BaseUseCase<Any, Flow<PagingData<MovieDetails>>>

class GetUpcomingMediatorUCImpl(
    private val remoteMoviesRepository: RemoteMoviesRepository,
    private val localPaginatedMoviesRepository: LocalPaginatedMoviesRepository,
    private val localRemoteKeyRepository: LocalRemoteKeyRepository,
) : GetUpcomingMediatorUC {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun invoke(params: Any): Flow<PagingData<MovieDetails>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = UpcomingMoviesMediatorPagingSource(
                localPaginatedMoviesRepository,
                localRemoteKeyRepository,
                remoteMoviesRepository
            ),
            pagingSourceFactory = {
                // FIXME: 11/15/21 avoid accessing database on the main thread
                localPaginatedMoviesRepository.pagingSource(MovieSourceType.UPCOMING)
            }
        ).flow
    }
}

class UpcomingMoviesMediatorPagingSource(
    localPaginatedMoviesRepository: LocalPaginatedMoviesRepository,
    localRemoteKeyRepository: LocalRemoteKeyRepository,
    remoteMoviesRepository: RemoteMoviesRepository,
) : BaseRemoteMediator(
    MovieSourceType.UPCOMING,
    localPaginatedMoviesRepository,
    localRemoteKeyRepository,
    remoteMoviesRepository,
)