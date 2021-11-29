package com.banyar.domain.usecase

import androidx.paging.*
import com.banyar.domain.paging.BaseRemoteMediator
import com.banyar.domain.paging.MovieSourceType
import com.banyar.domain.paging.PagingUtility
import com.banyar.domain.repository.LocalPaginatedMoviesRepository
import com.banyar.domain.repository.LocalRemoteKeyRepository
import com.banyar.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.flow.Flow

private val CATEGORIES = listOf(MovieSourceType.values())

interface GetCategoriesUC :
    BaseUseCase<Any, Flow<PagingData<MoviesMediatorPD>>>

class GetCategoriesUCImpl(
    private val localPaginatedMoviesRepository: LocalPaginatedMoviesRepository,
    private val localRemoteKeyRepository: LocalRemoteKeyRepository,
    private val remoteMoviesRepository: RemoteMoviesRepository,
) : GetCategoriesUC {

    override suspend fun invoke(params: Any) =
        Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = {
                CategoriesPagingSource(
                    localPaginatedMoviesRepository,
                    localRemoteKeyRepository,
                    remoteMoviesRepository
                )
            }
        ).flow
}

class CategoriesPagingSource(
    private val localPaginatedMoviesRepository: LocalPaginatedMoviesRepository,
    private val localRemoteKeyRepository: LocalRemoteKeyRepository,
    private val remoteMoviesRepository: RemoteMoviesRepository,
) : PagingSource<Int, MoviesMediatorPD>() {

    override fun getRefreshKey(state: PagingState<Int, MoviesMediatorPD>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesMediatorPD> {
        val response = listOf(
            PagingUtility.buildFavouriteMoviesMediator(localPaginatedMoviesRepository),
            PagingUtility.buildMoviesMediator(
                MovieSourceType.POPULAR,
                localRemoteKeyRepository, localPaginatedMoviesRepository, remoteMoviesRepository
            ),
            PagingUtility.buildMoviesMediator(
                MovieSourceType.UPCOMING,
                localRemoteKeyRepository, localPaginatedMoviesRepository, remoteMoviesRepository
            ),
            PagingUtility.buildMoviesMediator(
                MovieSourceType.TOP_RATED,
                localRemoteKeyRepository, localPaginatedMoviesRepository, remoteMoviesRepository
            ),
            PagingUtility.buildMoviesMediator(
                MovieSourceType.NOW_PLAYING,
                localRemoteKeyRepository, localPaginatedMoviesRepository, remoteMoviesRepository
            ),
        )

        return LoadResult.Page(
            data = response,
            prevKey = null,
            nextKey = null
        )
    }
}