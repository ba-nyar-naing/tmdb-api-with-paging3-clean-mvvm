package com.banyar.data.local.mapper

import com.banyar.data.local.model.FavouriteEntity
import com.banyar.data.local.model.PopularMovieEntity
import com.banyar.data.local.model.RemoteKeyEntity
import com.banyar.domain.model.Favourite
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.RemoteKey

internal fun Favourite.toEntity(): FavouriteEntity {
    return FavouriteEntity(id, title, posterPath, updatedAt)
}

internal fun MovieDetails.toEntity(): PopularMovieEntity {
    return PopularMovieEntity(title!!, posterPath!!, id!!)
}

internal fun RemoteKey.toEntity(): RemoteKeyEntity {
    return RemoteKeyEntity(movieId!!, prevKey, nextKey)
}