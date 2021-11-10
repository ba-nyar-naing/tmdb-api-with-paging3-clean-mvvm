package com.banyar.data.local.mapper

import com.banyar.data.local.model.FavouriteEntity
import com.banyar.domain.model.Favourite

internal fun FavouriteEntity.toDomain(): Favourite {
    return Favourite(id, title, posterPath, updatedAt)
}