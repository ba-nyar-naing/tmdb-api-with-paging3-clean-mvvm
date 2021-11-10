package com.banyar.data.local.mapper

import com.banyar.data.local.model.FavouriteEntity
import com.banyar.domain.model.Favourite

internal fun Favourite.toEntity(): FavouriteEntity {
    return FavouriteEntity(id, title, posterPath, updatedAt)
}