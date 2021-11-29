package com.banyar.domain.usecase

import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.Result
import com.banyar.domain.repository.LocalPaginatedMoviesRepository
import kotlinx.coroutines.flow.Flow

interface InsertFavouriteUC : BaseUseCase<MovieDetails, Flow<Result>>

class InsertFavouriteUCImpl(
    private val repository: LocalPaginatedMoviesRepository
) : InsertFavouriteUC {

    override suspend fun invoke(params: MovieDetails) =
        repository.insertFavourite(params)
}