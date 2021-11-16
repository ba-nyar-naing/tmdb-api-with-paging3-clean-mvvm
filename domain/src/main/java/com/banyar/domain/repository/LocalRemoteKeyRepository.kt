package com.banyar.domain.repository

import com.banyar.domain.model.RemoteKey
import com.banyar.domain.paging.MovieSourceType
import kotlinx.coroutines.flow.Flow

interface LocalRemoteKeyRepository {

    fun insertAll(movieSourceType: MovieSourceType, remoteKeys: List<RemoteKey>): Flow<Unit>

    fun clearAll(movieSourceType: MovieSourceType): Flow<Unit>

    fun getRemoteKey(movieSourceType: MovieSourceType, movieId: Int): Flow<RemoteKey?>
}