package com.banyar.domain.usecase

import com.banyar.domain.model.MovieDetails
import com.banyar.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetMovieDetailsBaseUC = BaseUseCase<Int, Flow<MovieDetails>>

class GetMovieDetailsUC @Inject constructor(
    private val repository: MoviesRepository
) : GetMovieDetailsBaseUC {

    override suspend operator fun invoke(params: Int) =
        repository.getDetails(params)
}