package com.banyar.domain.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.banyar.domain.repository.LocalPaginatedMoviesRepository
import com.banyar.domain.repository.LocalRemoteKeyRepository
import com.banyar.domain.repository.RemoteMoviesRepository
import com.banyar.domain.usecase.MoviesMediatorPD

object PagingUtility {

    @OptIn(ExperimentalPagingApi::class)
    fun buildMoviesMediator(
        movieSourceType: MovieSourceType,
        localRemoteKeyRepository: LocalRemoteKeyRepository,
        localPaginatedMoviesRepository: LocalPaginatedMoviesRepository,
        remoteMoviesRepository: RemoteMoviesRepository,
    ): MoviesMediatorPD {
        return MoviesMediatorPD(movieSourceType,
            Pager(
                config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                remoteMediator = MoviesMediatorPagingSource(
                    movieSourceType,
                    localPaginatedMoviesRepository,
                    localRemoteKeyRepository,
                    remoteMoviesRepository
                ),
                pagingSourceFactory = {
                    // FIXME: 11/15/21 avoid accessing database on the main thread
                    localPaginatedMoviesRepository.pagingSource(movieSourceType)
                }
            ).flow
        )
    }

    private class MoviesMediatorPagingSource(
        movieSourceType: MovieSourceType,
        localPaginatedMoviesRepository: LocalPaginatedMoviesRepository,
        localRemoteKeyRepository: LocalRemoteKeyRepository,
        remoteMoviesRepository: RemoteMoviesRepository,
    ) : BaseRemoteMediator(
        movieSourceType,
        localPaginatedMoviesRepository,
        localRemoteKeyRepository,
        remoteMoviesRepository,
    )

    @OptIn(ExperimentalPagingApi::class)
    fun buildFavouriteMoviesMediator(
        repository: LocalPaginatedMoviesRepository,
    ): MoviesMediatorPD {
        return MoviesMediatorPD(MovieSourceType.FAVOURITE,
            Pager(
                config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                pagingSourceFactory = {
                    repository.pagingSourceFavourite()
                }
            ).flow
        )
    }
}