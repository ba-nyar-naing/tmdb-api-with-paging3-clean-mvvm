package com.banyar.domain.usecase

import androidx.paging.PagingData
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.paging.MovieSourceType
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
}

data class MoviesMediatorPD(
    val type: MovieSourceType,
    val pagingData: Flow<PagingData<MovieDetails>>,
)