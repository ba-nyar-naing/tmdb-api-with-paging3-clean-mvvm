package com.banyar.data.remote.api

import com.banyar.data.remote.model.MovieDetailsResponse
import com.banyar.data.remote.model.PopularResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiService {

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): PopularResponse

    @GET("movie/{id}")
    suspend fun getDetails(
        @Path("id") id: Int
    ):MovieDetailsResponse

}