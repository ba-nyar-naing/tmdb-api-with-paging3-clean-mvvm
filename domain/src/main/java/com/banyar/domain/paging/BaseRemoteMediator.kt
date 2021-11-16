package com.banyar.domain.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.RemoteKey
import com.banyar.domain.repository.LocalPaginatedMoviesRepository
import com.banyar.domain.repository.LocalRemoteKeyRepository
import com.banyar.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.flow.first
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
abstract class BaseRemoteMediator(
    private val movieSourceType: MovieSourceType,
    private val localPaginatedMoviesRepository: LocalPaginatedMoviesRepository,
    private val localRemoteKeyRepository: LocalRemoteKeyRepository,
    private val remoteMoviesRepository: RemoteMoviesRepository
) : RemoteMediator<Int, MovieDetails>() {

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieDetails>): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { movieId ->
                localRemoteKeyRepository.getRemoteKey(movieSourceType, movieId).first()
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieDetails>): RemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                localRemoteKeyRepository.getRemoteKey(movieSourceType, movie.id!!).first()
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieDetails>): RemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                localRemoteKeyRepository.getRemoteKey(movieSourceType, movie.id!!).first()
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
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }
        try {
            val movies = getMovies(movieSourceType, page)
            val endOfPaginationReached = movies.isEmpty()
            if (loadType == LoadType.REFRESH) {
                localRemoteKeyRepository.clearAll(movieSourceType).first()
                localPaginatedMoviesRepository.clearAll(movieSourceType).first()
            }
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (endOfPaginationReached) null else page + 1
            val remoteKeys = movies.map {
                it.category = movieSourceType.toString()
                RemoteKey(
                    movieId = it.id,
                    prevKey = prevKey,
                    nextKey = nextKey,
                    category = movieSourceType.toString()
                )
            }

            localRemoteKeyRepository.insertAll(movieSourceType, remoteKeys).first()
            localPaginatedMoviesRepository.insertAll(movieSourceType, movies).first()
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
//        } catch (exception: HttpException) {
//            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getMovies(movieSourceType: MovieSourceType, page: Int): List<MovieDetails> {
        val response = when (movieSourceType) {
            MovieSourceType.POPULAR -> remoteMoviesRepository.getPopularMovies(page)
            MovieSourceType.UPCOMING -> remoteMoviesRepository.getUpcomingMovies(page)
        }.first()
        return response.movies
    }
}