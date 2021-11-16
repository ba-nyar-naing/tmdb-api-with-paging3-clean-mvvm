package com.banyar.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.paging.BaseRemoteMediator
import com.banyar.domain.repository.LocalPopularMoviesRepository
import com.banyar.domain.repository.LocalRemoteKeyRepository
import com.banyar.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.flow.Flow

interface GetPopularMediatorUC : BaseUseCase<Any, Flow<PagingData<MovieDetails>>>

class GetPopularMediatorUCImpl(
    private val remoteMoviesRepository: RemoteMoviesRepository,
    private val localPopularMoviesRepository: LocalPopularMoviesRepository,
    private val localRemoteKeyRepository: LocalRemoteKeyRepository,
) : GetPopularMediatorUC {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun invoke(params: Any): Flow<PagingData<MovieDetails>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = PopularMoviesMediatorPagingSource(
                localPopularMoviesRepository,
                localRemoteKeyRepository,
                remoteMoviesRepository
            ),
            pagingSourceFactory = {
                // FIXME: 11/15/21 avoid accessing database on the main thread
                localPopularMoviesRepository.pagingSource()
            }
        ).flow
    }

}

class PopularMoviesMediatorPagingSource(
    localPopularMoviesRepository: LocalPopularMoviesRepository,
    localRemoteKeyRepository: LocalRemoteKeyRepository,
    remoteMoviesRepository: RemoteMoviesRepository,
) : BaseRemoteMediator(
    localPopularMoviesRepository,
    localRemoteKeyRepository,
    remoteMoviesRepository,
)