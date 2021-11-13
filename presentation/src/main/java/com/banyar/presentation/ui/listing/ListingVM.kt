package com.banyar.presentation.ui.listing

import androidx.paging.PagingData
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.usecase.GetPopularUC
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListingVM @Inject constructor(
    private val getPopularUC: GetPopularUC,
) : BaseViewModel() {

    override fun init() {
    }

    suspend fun getPagingData(): Flow<PagingData<MovieDetails>> {
        return getPopularUC(Any())
    }
}