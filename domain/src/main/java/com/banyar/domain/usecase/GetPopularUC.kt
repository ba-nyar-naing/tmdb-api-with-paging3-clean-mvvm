package com.banyar.domain.usecase

import androidx.paging.*
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

interface GetPopularUC : BaseUseCase<Any, Flow<PagingData<MovieDetails>>>

class GetPopularUCImpl(
    private val repository: MoviesRepository
) : GetPopularUC {

    override suspend fun invoke(params: Any) =
        Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = {
                PopularMoviesPagingSource(repository)
            }
        ).flow
}

class PopularMoviesPagingSource(
    private val repository: MoviesRepository
) : PagingSource<Int, MovieDetails>() {

    override fun getRefreshKey(state: PagingState<Int, MovieDetails>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetails> {
        val currentLoadingPageKey = params.key ?: 1

        return try {
            val response = repository.getPopularMovies(currentLoadingPageKey).first()

            LoadResult.Page(
                data = response.movies,
                prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey,
                nextKey = currentLoadingPageKey + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}