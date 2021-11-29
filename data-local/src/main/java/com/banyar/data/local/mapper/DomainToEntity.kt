package com.banyar.data.local.mapper

import com.banyar.data.local.model.PaginatedMovieEntity
import com.banyar.data.local.model.RemoteKeyEntity
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.RemoteKey

internal fun MovieDetails.toEntity(): PaginatedMovieEntity {
    return PaginatedMovieEntity(
        title ?: "",
        posterPath ?: "",
        id ?: -1,
        category ?: "",
        updatedAt = System.currentTimeMillis().toString()
    )
}

internal fun RemoteKey.toEntity(): RemoteKeyEntity {
    return RemoteKeyEntity(category, movieId, prevKey, nextKey)
}