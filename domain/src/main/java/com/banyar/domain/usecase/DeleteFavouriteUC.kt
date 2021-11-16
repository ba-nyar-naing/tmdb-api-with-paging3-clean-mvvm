package com.banyar.domain.usecase

import com.banyar.domain.model.Result
import com.banyar.domain.repository.LocalFavouritesRepository
import kotlinx.coroutines.flow.Flow

interface DeleteFavouriteUC : BaseUseCase<Int, Flow<Result>>

class DeleteFavouriteUCImpl(
    private val repository: LocalFavouritesRepository
) : DeleteFavouriteUC {

    override suspend fun invoke(params: Int) =
        repository.deleteFavourite(params)
}