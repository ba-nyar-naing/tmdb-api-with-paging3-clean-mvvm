package com.banyar.domain.usecase

import com.banyar.domain.model.Favourite
import com.banyar.domain.model.Result
import com.banyar.domain.repository.LocalFavouritesRepository
import kotlinx.coroutines.flow.Flow

interface InsertFavouriteUC : BaseUseCase<Favourite, Flow<Result>>

class InsertFavouriteUCImpl(
    private val repository: LocalFavouritesRepository
) : InsertFavouriteUC {

    override suspend fun invoke(params: Favourite) =
        repository.insertFavourite(params)
}