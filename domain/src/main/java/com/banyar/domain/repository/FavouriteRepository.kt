package com.banyar.domain.repository

import com.banyar.domain.model.Favourite
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    fun getAllFavourites(page: Int): Flow<List<Favourite>>

}