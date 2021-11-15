package com.banyar.data.local.mapper

import com.banyar.data.local.model.FavouriteEntity
import com.banyar.data.local.model.RemoteKeyEntity
import com.banyar.domain.model.Favourite
import com.banyar.domain.model.RemoteKey

internal fun FavouriteEntity.toDomain(): Favourite {
    return Favourite(id, title, posterPath, updatedAt)
}

//internal fun MovieEntity.toDomain(): MovieDetails {
//    return MovieDetails(id = id, title = title, posterPath = posterPath)
//}

internal fun RemoteKeyEntity.toDomain(): RemoteKey {
    return RemoteKey(movieId, prevKey, nextKey)
}