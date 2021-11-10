package com.banyar.domain.usecase

import androidx.paging.*
import com.banyar.domain.model.Favourite
import com.banyar.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

interface GetFavouritesUC : BaseUseCase<Any, Flow<PagingData<Favourite>>>

class GetFavouritesUCImpl(
    private val repository: FavouriteRepository
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
    private val repository: FavouriteRepository
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