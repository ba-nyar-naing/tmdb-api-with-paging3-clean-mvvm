package com.banyar.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.model.Result
import com.banyar.domain.paging.MovieSourceType
import com.banyar.domain.usecase.DeleteFavouriteUC
import com.banyar.domain.usecase.GetDetailsUC
import com.banyar.domain.usecase.GetFavouriteStatUC
import com.banyar.domain.usecase.InsertFavouriteUC
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val getDetailsUC: GetDetailsUC,
    private val insertFavouriteUC: InsertFavouriteUC,
    private val deleteFavouriteUC: DeleteFavouriteUC,
    private val getFavouriteStatUC: GetFavouriteStatUC,
) : BaseViewModel() {

    val movieDetails by lazy { MutableLiveData<MovieDetails>() }

    val uiState by lazy { MutableLiveData<DetailUIState>() }

    override fun init() {
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            getDetailsUC(id).collect {
                movieDetails.postValue(it)
            }
        }
    }

    fun checkFavouriteStats(id: Int) {
        viewModelScope.launch {
            getFavouriteStatUC(id).collect { favourite ->
                if (favourite == null) {
                    uiState.postValue(DetailUIState.ShowSaveFavourite)
                } else {
                    uiState.postValue(DetailUIState.ShowDeleteFavourite)
                }
            }
        }
    }

    fun insertFavourite() {
        viewModelScope.launch {
            val movieDetails = movieDetails.value ?: return@launch
            movieDetails.category = MovieSourceType.FAVOURITE.toString()
            insertFavouriteUC(movieDetails).collect { result ->
                when (result) {
                    Result.SUCCESS -> uiState.postValue(DetailUIState.ShowDeleteFavourite)
                    Result.FAILURE -> uiState.postValue(DetailUIState.FavouriteSaveFailed)
                }
            }
        }
    }

    fun deleteFavourite() {
        viewModelScope.launch {
            val movieDetails = movieDetails.value ?: return@launch
            deleteFavouriteUC(movieDetails.id!!).collect { result ->
                when (result) {
                    Result.SUCCESS -> uiState.postValue(DetailUIState.ShowSaveFavourite)
                    Result.FAILURE -> uiState.postValue(DetailUIState.FavouriteDeleteFailed)
                }
            }
        }
    }
}

sealed class DetailUIState {
    object ShowDeleteFavourite : DetailUIState()
    object ShowSaveFavourite : DetailUIState()
    object FavouriteSaveFailed : DetailUIState()
    object FavouriteDeleteFailed : DetailUIState()
}