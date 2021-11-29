package com.banyar.data.local.mapper

import com.banyar.data.local.model.RemoteKeyEntity
import com.banyar.domain.model.RemoteKey

internal fun RemoteKeyEntity.toDomain(): RemoteKey {
    return RemoteKey(category, movieId, prevKey, nextKey)
}