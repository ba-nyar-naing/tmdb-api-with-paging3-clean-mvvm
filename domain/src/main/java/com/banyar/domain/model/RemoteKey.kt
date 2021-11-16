package com.banyar.domain.model

data class RemoteKey(
    var category: String,
    val movieId: Int?,
    val prevKey: Int?,
    val nextKey: Int?
)
