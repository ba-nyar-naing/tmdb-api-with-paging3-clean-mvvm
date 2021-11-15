package com.banyar.data.local.repository

import com.banyar.data.local.dao.MovieDAO
import com.banyar.data.local.dao.RemoteKeyDao
import com.banyar.data.local.mapper.toDomain
import com.banyar.data.local.mapper.toEntity
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.RemoteKey
import com.banyar.domain.repository.CachedMoviesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CachedMoviesRepositoryImpl @Inject constructor(
    private val movieDAO: MovieDAO,
    private val remoteKeyDao: RemoteKeyDao,
) : CachedMoviesRepository {

    override fun insertAll(movieDetails: List<MovieDetails>) = flow {
        movieDetails.map {
            movieDAO.insert(it)
        }
        emit(1L)
    }

    override fun pagingSource() = movieDAO.pagingSource()

    override fun clearAll() = flow {
        emit(movieDAO.clearAll())
    }

    override fun clearRemoteKeys() = flow {
        emit(remoteKeyDao.clearRemoteKeys())
    }

    override fun insertAllRemoteKeys(remoteKeys: List<RemoteKey>) = flow {
        remoteKeys.map {
            remoteKeyDao.insert(it.toEntity())
        }
        emit(1L)
    }

    override fun remoteKeysRepoId(movieId: Int) = flow {
        val key = remoteKeyDao.remoteKeysRepoId(movieId)
        val keyDomain = key?.toDomain()!!
        emit(keyDomain)
    }
}