package com.banyar.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIInterceptor @Inject constructor() : Interceptor {

    // TODO: 10/30/21 remove later
    val token =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjMzVkMjcyZjdjOWYyMGU3YTY3Y2M0YTRiNjFjYjA5MiIsInN1YiI6IjVjYjVlOGE1YzNhMzY4MWIwZDgxMzU0MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.66cO8YBpmm4ea6EKV4b7h0DuwUd5jPOnPq9BgGKNZhc"

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Authorization", "Bearer $token")
        return chain.proceed(builder.build())
    }

}