package com.banyar.domain.repository

import com.banyar.domain.model.RemoteKey
import kotlinx.coroutines.flow.Flow

interface LocalRemoteKeyRepository {

    fun insertAll(remoteKeys: List<RemoteKey>): Flow<Unit>

    fun clearAll(): Flow<Unit>

    fun getRemoteKey(movieId: Int): Flow<RemoteKey>
}