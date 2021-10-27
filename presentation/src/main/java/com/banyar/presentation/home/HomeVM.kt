package com.banyar.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.banyar.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: HomeRepository<HomeContract.IViewModel>
) : BaseViewModel(),  HomeContract.IViewModel {

    init {
        repository.onAttach(this)
    }

    override fun getPopularMovies() {
        Timber.d("getPopularMovies: ")
        viewModelScope.launch {
            repository.getPopularMovies()
        }
    }

    override fun onTest(message: String) {
        Timber.d("onTest: $message")
    }

}