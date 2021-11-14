package com.banyar.presentation.ui.home

import androidx.paging.PagingData
import com.banyar.domain.model.Favourite
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.usecase.GetFavouritesUC
import com.banyar.domain.usecase.GetPopularMediatorUC
import com.banyar.domain.usecase.GetPopularUC
import com.banyar.domain.usecase.GetUpcomingUC
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val getPopularUC: GetPopularUC,
    private val getPopularMediatorUC: GetPopularMediatorUC,
    private val getUpcomingUC: GetUpcomingUC,
    private val getFavouritesUC: GetFavouritesUC,
) : BaseViewModel() {

    override fun init() {
    }

    suspend fun getPopularPagingData(): Flow<PagingData<MovieDetails>> {
//        return getPopularUC(Any())
        return getPopularMediatorUC(Any())
    }

    suspend fun getUpcomingPagingData(): Flow<PagingData<MovieDetails>> {
        return getUpcomingUC(Any())
    }

    suspend fun getFavouritePagingData(): Flow<PagingData<Favourite>> {
        return getFavouritesUC(Any())
    }
}