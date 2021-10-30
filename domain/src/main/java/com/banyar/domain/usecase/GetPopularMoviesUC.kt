package com.banyar.domain.usecase

import androidx.paging.*
import com.banyar.domain.model.Movie
import com.banyar.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

typealias GetPopularMoviesBaseUC = BaseUseCase<Any, Flow<PagingData<Movie>>>

class GetPopularMoviesUC @Inject constructor(
    private val repository: MoviesRepository
) : GetPopularMoviesBaseUC {

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
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
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