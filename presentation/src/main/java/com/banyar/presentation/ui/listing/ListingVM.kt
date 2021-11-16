package com.banyar.presentation.ui.listing

import androidx.paging.PagingData
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.usecase.GetPopularMediatorUC
import com.banyar.domain.usecase.GetUpcomingMediatorUC
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListingVM @Inject constructor(
    private val getPopularMediatorUC: GetPopularMediatorUC,
    private val getUpcomingMediatorUC: GetUpcomingMediatorUC,
) : BaseViewModel() {

    override fun init() {
    }

    suspend fun getPopularPagingData(): Flow<PagingData<MovieDetails>> {
        return getPopularMediatorUC(Any())
    }

    suspend fun getUpcomingPagingData(): Flow<PagingData<MovieDetails>> {
        return getUpcomingMediatorUC(Any())
    }
}