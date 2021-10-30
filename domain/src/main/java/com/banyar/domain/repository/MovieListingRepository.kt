package com.banyar.domain.repository

import com.banyar.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

interface MovieListingRepository {

    fun getPopular(page: Int): Flow<List<Movie>>
}