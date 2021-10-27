package com.banyar.presentation.detail

import com.banyar.presentation.base.BaseRepository
import timber.log.Timber
import javax.inject.Inject

class DetailRepository<VM : DetailContract.IViewModel>
@Inject constructor() : BaseRepository<VM>(), DetailContract.IRepository<VM> {

    override suspend fun getMovieDetail() {
        Timber.d("getMovieDetail: ")
        vm.onTest("return fake movie data from repository")
    }

}