package com.banyar.domain.model

data class MovieDetails(
    var adult: Boolean?,
    var backdropPath: String?,
    var budget: Int?,
    var genres: List<Genre>?,
    var homepage: String?,
    var id: Int?,
    var imdbId: String?,
    var originalLanguage: String?,
    var originalTitle: String?,
    var overview: String?,
    var popularity: Double?,
    var posterPath: String?,
    var releaseDate: String?,
    var revenue: Int?,
    var runtime: Int?,
    var status: String?,
    var tagline: String?,
    var title: String?,
    var video: Boolean?,
    var voteAverage: Double?,
    var voteCount: Int?
)

fun MovieDetails.toFavourite() = Favourite(
    id!!,
    title!!,
    posterPath!!,
    System.currentTimeMillis().toString()
)