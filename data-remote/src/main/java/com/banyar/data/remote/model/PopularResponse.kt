package com.banyar.data.remote.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PopularResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<MovieResponse>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)