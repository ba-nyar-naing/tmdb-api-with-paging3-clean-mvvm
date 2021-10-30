package com.banyar.domain.usecase

import com.banyar.domain.model.Movie
import com.banyar.domain.repository.MovieListingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetPopularBaseUC = BaseUseCase<Int, Flow<List<Movie>>>

class GetPopularUC @Inject constructor(
    private val movieListingRepository: MovieListingRepository
) : GetPopularBaseUC {

    override suspend operator fun invoke(params: Int) =
        movieListingRepository.getPopular(params)

}