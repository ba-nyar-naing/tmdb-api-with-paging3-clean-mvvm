package com.banyar.domain.usecase

import com.banyar.domain.model.Favourite
import com.banyar.domain.repository.LocalFavouritesRepository
import kotlinx.coroutines.flow.Flow

interface GetFavouriteStatUC : BaseUseCase<Int, Flow<Favourite?>>

class GetFavouriteStatUCImpl(
    private val repository: LocalFavouritesRepository
) : GetFavouriteStatUC {

    override suspend fun invoke(params: Int) =
        repository.getFavourite(params)
}