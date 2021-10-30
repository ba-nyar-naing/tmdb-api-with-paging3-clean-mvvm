package com.banyar.presentation.ui.home

import com.banyar.presentation.ui.base.BaseRepository
import timber.log.Timber
import javax.inject.Inject

class HomeRepository<VM : HomeContract.IViewModel>
@Inject constructor() : BaseRepository<VM>(), HomeContract.IRepository<VM> {

    override suspend fun getPopularMovies() {
        Timber.d("getPopularMovies: return test data")
        vm.onTest("return test message from repository")
    }

}