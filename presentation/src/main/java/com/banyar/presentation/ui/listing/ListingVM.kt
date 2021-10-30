package com.banyar.presentation.ui.listing

import androidx.lifecycle.viewModelScope
import com.banyar.domain.usecase.GetPopularUC
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListingVM @Inject constructor(
    private val getPopularUC: GetPopularUC,
) : BaseViewModel() {

    fun getPopularMovies() {
        Timber.d("getPopularMovies: ")
        viewModelScope.launch {
            getPopularUC(1).collect { movies ->
                Timber.d("getPopularMovies: movies =>${movies.size}")
            }
        }
    }

}