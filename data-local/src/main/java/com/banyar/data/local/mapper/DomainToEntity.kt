package com.banyar.data.local.mapper

import com.banyar.data.local.model.FavouriteEntity
import com.banyar.data.local.model.PaginatedMovieEntity
import com.banyar.data.local.model.RemoteKeyEntity
import com.banyar.domain.model.Favourite
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.RemoteKey

internal fun Favourite.toEntity(): FavouriteEntity {
    return FavouriteEntity(id, title, posterPath, updatedAt)
}

internal fun MovieDetails.toEntity(): PaginatedMovieEntity {
    return PaginatedMovieEntity(title ?: "", posterPath ?: "", id ?: -1, category ?: "")
}

internal fun RemoteKey.toEntity(): RemoteKeyEntity {
    return RemoteKeyEntity(category, movieId, prevKey, nextKey)
}