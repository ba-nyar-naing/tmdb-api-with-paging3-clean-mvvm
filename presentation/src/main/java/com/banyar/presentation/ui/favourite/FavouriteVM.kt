package com.banyar.presentation.ui.favourite

import androidx.paging.PagingData
import com.banyar.domain.model.Favourite
import com.banyar.domain.usecase.GetFavouritesUC
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavouriteVM @Inject constructor(
    private val getFavouritesUC: GetFavouritesUC
) : BaseViewModel() {

    override fun init() {
    }

    suspend fun getPagingData(): Flow<PagingData<Favourite>> {
        return getFavouritesUC(Any())
    }
}