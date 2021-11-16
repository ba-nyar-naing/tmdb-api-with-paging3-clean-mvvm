package com.banyar.data.local.repository

import com.banyar.data.local.dao.RemoteKeyDAO
import com.banyar.data.local.mapper.toDomain
import com.banyar.data.local.mapper.toEntity
import com.banyar.domain.model.RemoteKey
import com.banyar.domain.repository.LocalRemoteKeyRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRemoteKeyRepositoryImpl @Inject constructor(
    private val remoteKeyDAO: RemoteKeyDAO,
) : LocalRemoteKeyRepository {

    override fun insertAll(remoteKeys: List<RemoteKey>) = flow {
        remoteKeys.map { remoteKeyDAO.insert(it.toEntity()) }
        emit(Unit)
    }

    override fun clearAll() = flow {
        emit(remoteKeyDAO.deleteAll())
    }

    override fun getRemoteKey(movieId: Int) = flow {
        val key = remoteKeyDAO.getRemoteKey(movieId)
        val keyDomain = key?.toDomain()
        emit(keyDomain)
    }
}