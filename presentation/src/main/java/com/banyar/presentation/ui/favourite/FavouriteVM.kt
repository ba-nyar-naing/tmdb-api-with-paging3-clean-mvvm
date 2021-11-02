package com.banyar.presentation.ui.favourite

import androidx.paging.PagingData
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.usecase.GetFavouriteMoviesUC
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavouriteVM @Inject constructor(
    private val getFavouriteMoviesUC: GetFavouriteMoviesUC
) : BaseViewModel() {

    override fun init() {
    }

    suspend fun getPagingData(): Flow<PagingData<MovieDetails>> {
        return getFavouriteMoviesUC(Any())
    }
}