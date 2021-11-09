package com.banyar.data.local.repository

import com.banyar.domain.model.Favourite
import com.banyar.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor() : FavouriteRepository {

    override fun getAllFavourites(page: Int): Flow<List<Favourite>> = flow {
        if (page == 5) return@flow
        emit(listOf(Favourite(1, "Hello", "", "")))
    }
}