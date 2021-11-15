package com.banyar.domain.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.RemoteKey
import com.banyar.domain.repository.CachedMoviesRepository
import com.banyar.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
abstract class BaseRemoteMediator(
    private val query: String,
    private val cachedMoviesRepository: CachedMoviesRepository,
    private val moviesRepository: MoviesRepository
) : RemoteMediator<Int, MovieDetails>() {

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MovieDetails>
    ): RemoteKey? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                cachedMoviesRepository.remoteKeysRepoId(repoId).first()
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieDetails>): RemoteKey? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                // Get the remote keys of the first items retrieved
                cachedMoviesRepository.remoteKeysRepoId(repo.id!!).first()
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieDetails>): RemoteKey? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                // Get the remote keys of the last item retrieved
                cachedMoviesRepository.remoteKeysRepoId(repo.id!!).first()
            }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieDetails>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }
        }
        try {
            val response = moviesRepository.getPopularMovies(page).first()
            val movies = response.movies
            val endOfPaginationReached = movies.isEmpty()
            if (loadType == LoadType.REFRESH) {
                cachedMoviesRepository.clearRemoteKeys()
                cachedMoviesRepository.clearAll()
            }
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (endOfPaginationReached) null else page + 1
            val keys = movies.map {
                RemoteKey(repoId = it.id, prevKey = prevKey, nextKey = nextKey)
            }

            cachedMoviesRepository.insertAllRemoteKeys(keys).firstOrNull()
            cachedMoviesRepository.insertAll(movies).firstOrNull()
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
//        } catch (exception: HttpException) {
//            return MediatorResult.Error(exception)
        }
    }
}