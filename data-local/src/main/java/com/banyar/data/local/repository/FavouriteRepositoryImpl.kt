package com.banyar.data.local.repository

import com.banyar.data.local.dao.FavouriteDAO
import com.banyar.data.local.mapper.toDomain
import com.banyar.data.local.mapper.toEntity
import com.banyar.domain.model.Favourite
import com.banyar.domain.model.Result
import com.banyar.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteDAO: FavouriteDAO
) : FavouriteRepository {

    override fun getFavourites(pageSize: Int, pageIndex: Int) = flow {
        val result = favouriteDAO.get(pageSize, pageIndex)
        val favourites = result.map { it.toDomain() }
        emit(favourites)
    }

    override fun insertFavourite(favourite: Favourite) = flow {
        val result = favouriteDAO.insert(favourite.toEntity())
        if (result > 0) emit(Result.SUCCESS)
        else emit(Result.FAILURE)
    }

    override fun deleteFavourite(id: Int) = flow {
        val result = favouriteDAO.delete(id)
        if (result > 0) emit(Result.SUCCESS)
        else emit(Result.FAILURE)
    }
}