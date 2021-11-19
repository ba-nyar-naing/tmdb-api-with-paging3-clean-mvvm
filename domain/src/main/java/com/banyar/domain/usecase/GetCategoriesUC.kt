package com.banyar.domain.usecase

import androidx.paging.*
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.paging.MovieSourceType
import kotlinx.coroutines.flow.Flow

interface GetCategoriesUC :
    BaseUseCase<Any, Flow<PagingData<Pair<MovieSourceType, BaseUseCase<Any, Flow<PagingData<MovieDetails>>>>>>>

class GetCategoriesUCImpl(
    private val popular: GetPopularMediatorUC,
    private val upcoming: GetUpcomingMediatorUC,
) : GetCategoriesUC {

    override suspend fun invoke(params: Any) =
        Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = {
                CategoriesPagingSource(popular, upcoming)
            }
        ).flow
}

class CategoriesPagingSource(
    private val popular: GetPopularMediatorUC,
    private val upcoming: GetUpcomingMediatorUC,
) : PagingSource<Int,
        Pair<MovieSourceType, BaseUseCase<Any, Flow<PagingData<MovieDetails>>>>>() {

    override fun getRefreshKey(state: PagingState<Int, Pair<MovieSourceType, BaseUseCase<Any, Flow<PagingData<MovieDetails>>>>>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pair<MovieSourceType, BaseUseCase<Any, Flow<PagingData<MovieDetails>>>>> {
//        val categories = listOf(MovieSourceType.UPCOMING, MovieSourceType.POPULAR)
        val response = listOf(
            Pair(MovieSourceType.POPULAR, popular),
            Pair(MovieSourceType.UPCOMING, upcoming)
        )
        return LoadResult.Page(
            data = response,
            prevKey = null,
            nextKey = null
        )
    }
}