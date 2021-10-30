package com.banyar.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val repository: HomeRepository<HomeContract.IViewModel>
) : BaseViewModel(), HomeContract.IViewModel {

    init {
        repository.onAttach(this)
    }

    override fun getPopularMovies() {
        Timber.d("getPopularMovies: ")
        viewModelScope.launch {
            repository.getPopularMovies()
        }
    }

}