package com.banyar.domain.usecase

import com.banyar.domain.model.Movie
import com.banyar.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetPopularBaseUC = BaseUseCase<Int, Flow<List<Movie>>>

class GetPopularUC @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetPopularBaseUC {

    override suspend operator fun invoke(params: Int) =
        moviesRepository.getPopular(params)

}