package com.banyar.data.remote.api

import com.banyar.data.remote.model.MovieDetailsResponse
import com.banyar.data.remote.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiService {

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int,
    ): MovieListResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int,
    ): MovieListResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int,
    ): MovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int,
    ): MovieListResponse

    @GET("movie/{id}")
    suspend fun getDetails(
        @Path("id") id: Int
    ): MovieDetailsResponse
}