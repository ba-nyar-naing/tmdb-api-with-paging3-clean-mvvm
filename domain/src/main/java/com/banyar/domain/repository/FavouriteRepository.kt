package com.banyar.domain.repository

import com.banyar.domain.model.Favourite
import com.banyar.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    fun getFavourites(pageSize: Int, pageIndex: Int): Flow<List<Favourite>>

    fun insertFavourite(favourite: Favourite): Flow<Result>

    fun deleteFavourite(id: Int): Flow<Result>
}