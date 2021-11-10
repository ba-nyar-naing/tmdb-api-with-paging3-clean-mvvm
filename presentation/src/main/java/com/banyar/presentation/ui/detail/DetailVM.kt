package com.banyar.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.banyar.domain.model.Favourite
import com.banyar.domain.model.MovieDetails
import com.banyar.domain.usecase.GetDetailsUC
import com.banyar.domain.usecase.InsertFavouriteUC
import com.banyar.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val getDetailsUC: GetDetailsUC,
    private val insertFavouriteUC: InsertFavouriteUC
) : BaseViewModel() {

    val movieDetails by lazy { MutableLiveData<MovieDetails>() }

    override fun init() {
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            getDetailsUC(id).collectLatest {
                movieDetails.postValue(it)
            }
        }
    }

    fun insertFavourite(favourite: Favourite) {
        viewModelScope.launch {
            insertFavouriteUC(favourite).collectLatest { result ->
                Timber.d("insertFavourite: result: $result")
            }
        }
    }
}