package com.banyar.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.flow.first

enum class MovieSourceType {
    POPULAR,
    UPCOMING
}

abstract class BaseMoviesPagingSource(
    private val source: MovieSourceType,
    private val repository: RemoteMoviesRepository
) : PagingSource<Int, MovieDetails>() {

    override fun getRefreshKey(state: PagingState<Int, MovieDetails>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetails> {
        val currentLoadingPageKey = params.key ?: 1

        return try {
            val response = when (source) {
                MovieSourceType.POPULAR -> repository.getPopularMovies(currentLoadingPageKey)
                MovieSourceType.UPCOMING -> repository.getUpcomingMovies(currentLoadingPageKey)
            }.first()

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