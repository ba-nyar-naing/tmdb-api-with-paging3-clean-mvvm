package com.banyar.presentation.ui.home

import androidx.paging.PagingData
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.usecase.GetPopularUC
import com.banyar.domain.usecase.GetUpcomingUC
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val getPopularUC: GetPopularUC,
    private val getUpcomingUC: GetUpcomingUC,
) : BaseViewModel() {

    override fun init() {
    }

    suspend fun getPopularPagingData(): Flow<PagingData<MovieDetails>> {
        return getPopularUC(Any())
    }

    suspend fun getUpcomingPagingData(): Flow<PagingData<MovieDetails>> {
        return getUpcomingUC(Any())
    }
}