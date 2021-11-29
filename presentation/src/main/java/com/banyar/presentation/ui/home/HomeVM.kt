package com.banyar.presentation.ui.home

import androidx.paging.PagingData
import com.banyar.domain.usecase.GetCategoriesUC
import com.banyar.domain.usecase.MoviesMediatorPD
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val getCategoriesUC: GetCategoriesUC,
) : BaseViewModel() {

    override fun init() {
    }

    suspend fun getCategoryPagingData(): Flow<PagingData<MoviesMediatorPD>> {
        return getCategoriesUC(Any())
    }
}