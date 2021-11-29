package com.banyar.domain.paging

import androidx.paging.*
import com.banyar.domain.model.Favourite
import com.banyar.domain.repository.LocalFavouritesRepository
import com.banyar.domain.repository.LocalPaginatedMoviesRepository
import com.banyar.domain.repository.LocalRemoteKeyRepository
import com.banyar.domain.repository.RemoteMoviesRepository
import com.banyar.domain.usecase.GetFavouritesUC
import com.banyar.domain.usecase.MoviesMediatorPD
import kotlinx.coroutines.flow.first

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


    class GetFavouritesUCImpl(
        private val repository: LocalFavouritesRepository
    ) : GetFavouritesUC {

        override suspend fun invoke(params: Any) =
            Pager(
                config = PagingConfig(pageSize = 1, enablePlaceholders = false),
                pagingSourceFactory = {
                    FavouriteMoviesPagingSource(repository)
                }
            ).flow
    }

    private const val PAGE_SIZE = 10

    class FavouriteMoviesPagingSource(
        private val repository: LocalFavouritesRepository
    ) : PagingSource<Int, Favourite>() {

        override fun getRefreshKey(state: PagingState<Int, Favourite>): Int? = state.anchorPosition

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Favourite> {
            val currentLoadingPageKey = params.key ?: 0

            return try {
                val response = repository.getFavourites(
                    pageSize = PAGE_SIZE,
                    pageIndex = currentLoadingPageKey
                ).first()

                if (response.isEmpty()) {
                    throw Exception("List is empty")
                } else {
                    LoadResult.Page(
                        data = response,
                        prevKey = if (currentLoadingPageKey == 0) null else currentLoadingPageKey,
                        nextKey = currentLoadingPageKey + 1
                    )
                }
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }
}