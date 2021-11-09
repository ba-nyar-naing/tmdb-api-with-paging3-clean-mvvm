package com.banyar.domain.usecase

import androidx.paging.*
import com.banyar.domain.model.Favourite
import com.banyar.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

typealias GetFavouriteMoviesBaseUC = BaseUseCase<Any, Flow<PagingData<Favourite>>>

@Singleton
class GetFavouriteMoviesUC @Inject constructor(
    private val repository: FavouriteRepository
) : GetFavouriteMoviesBaseUC {

    override suspend fun invoke(params: Any) =
        Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = {
                FavouriteMoviesPagingSource(repository)
            }
        ).flow
}

class FavouriteMoviesPagingSource(
    private val repository: FavouriteRepository
) : PagingSource<Int, Favourite>() {

    override fun getRefreshKey(state: PagingState<Int, Favourite>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Favourite> {
        val currentLoadingPageKey = params.key ?: 1

        return try {
            val response = repository.getAllFavourites(currentLoadingPageKey).first()

            LoadResult.Page(
                data = response,
                prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey,
                nextKey = currentLoadingPageKey + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}