package com.banyar.domain.usecase

import com.banyar.domain.model.MovieDetails
import com.banyar.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

interface GetDetailsUC : BaseUseCase<Int, Flow<MovieDetails>>

class GetDetailsUCImpl(
    private val repository: MoviesRepository
) : GetDetailsUC {

    override suspend operator fun invoke(params: Int) =
        repository.getDetails(params)
}