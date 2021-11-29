package com.banyar.domain.usecase

import com.banyar.domain.model.MovieDetails
import com.banyar.domain.repository.LocalPaginatedMoviesRepository
import kotlinx.coroutines.flow.Flow

interface GetFavouriteStatUC : BaseUseCase<Int, Flow<MovieDetails?>>

class GetFavouriteStatUCImpl(
    private val repository: LocalPaginatedMoviesRepository
) : GetFavouriteStatUC {

    override suspend fun invoke(params: Int) =
        repository.getFavourite(params)
}