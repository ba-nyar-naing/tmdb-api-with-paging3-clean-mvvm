package com.banyar.data.remote.mapper

import com.banyar.data.remote.model.MovieResponse
import com.banyar.domain.model.Movie

internal fun MovieResponse.toDomain(): Movie {
    return Movie(
        this.adult
    )
}