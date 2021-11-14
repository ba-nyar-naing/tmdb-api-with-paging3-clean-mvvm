package com.banyar.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieDetails(
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var budget: Int? = null,
    @Ignore var genres: List<Genre>? = null,
    var homepage: String? = null,
    @PrimaryKey var id: Int? = null,
    var imdbId: String? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null,
)

fun MovieDetails.toFavourite() = Favourite(
    id!!,
    title!!,
    posterPath!!,
    System.currentTimeMillis().toString()
)