package com.banyar.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.usecase.GetMovieDetailsUC
import com.banyar.domain.usecase.GetPopularMoviesUC
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val getMovieDetailsUC: GetMovieDetailsUC,
) : BaseViewModel() {

    val movieDetails by lazy { MutableLiveData<MovieDetails>() }

    override fun init() {
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            getMovieDetailsUC(id).collect { movieDetails.postValue(it) }
        }
    }
}