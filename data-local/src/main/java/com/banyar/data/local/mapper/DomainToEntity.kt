package com.banyar.data.local.mapper

import com.banyar.data.local.model.FavouriteEntity
import com.banyar.data.local.model.RemoteKeyEntity
import com.banyar.domain.model.Favourite
import com.banyar.domain.model.RemoteKey

internal fun Favourite.toEntity(): FavouriteEntity {
    return FavouriteEntity(id, title, posterPath, updatedAt)
}

//internal fun MovieDetails.toEntity(): MovieEntity {
//    return MovieEntity(id!!, title!!, posterPath!!)
//}

internal fun RemoteKey.toEntity(): RemoteKeyEntity {
    return RemoteKeyEntity(repoId!!, prevKey, nextKey)
}