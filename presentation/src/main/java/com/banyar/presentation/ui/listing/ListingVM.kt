package com.banyar.presentation.ui.listing

import androidx.paging.PagingData
import com.banyar.domain.model.Movie
import com.banyar.domain.usecase.GetPopularMoviesUC
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListingVM @Inject constructor(
    private val getPopularMoviesUC: GetPopularMoviesUC,
) : BaseViewModel() {

    suspend fun getPagingData(): Flow<PagingData<Movie>> {
        return getPopularMoviesUC(Any())
    }
}