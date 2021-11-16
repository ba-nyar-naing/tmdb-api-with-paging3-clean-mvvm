package com.banyar.domain.usecase

import com.banyar.domain.model.MovieDetails
import com.banyar.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.flow.Flow

interface GetDetailsUC : BaseUseCase<Int, Flow<MovieDetails>>

class GetDetailsUCImpl(
    private val repository: RemoteMoviesRepository
) : GetDetailsUC {

    override suspend operator fun invoke(params: Int) =
        repository.getDetails(params)
}