package com.banyar.domain.repository

import androidx.paging.PagingSource
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.RemoteKey
import kotlinx.coroutines.flow.Flow

interface CachedMoviesRepository {

    fun insertAll(movieDetails: List<MovieDetails>): Flow<Long>

    fun pagingSource(): PagingSource<Int, MovieDetails>

    fun clearAll(): Flow<Unit>

    fun clearRemoteKeys(): Flow<Unit>

    fun insertAllRemoteKeys(remoteKeys: List<RemoteKey>): Flow<Long>

    fun remoteKeysRepoId(repoId: Int): Flow<RemoteKey>
}