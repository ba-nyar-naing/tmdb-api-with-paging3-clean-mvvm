package com.banyar.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.banyar.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: DetailRepository<DetailContract.IViewModel>
) : BaseViewModel(), DetailContract.IViewModel {

    init {
        repository.onAttach(this)
    }

    override fun getMovieDetail() {
        Timber.d("getMovieDetail: ")
        viewModelScope.launch {
            repository.getMovieDetail()
        }
    }

}