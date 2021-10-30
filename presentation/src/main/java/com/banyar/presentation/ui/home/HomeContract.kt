package com.banyar.presentation.ui.home

import com.banyar.presentation.ui.base.BaseContract

object HomeContract {

    interface IViewModel : BaseContract.IViewModel {
        fun getPopularMovies()
    }

    interface IRepository<VM : IViewModel> : BaseContract.IRepository<VM> {
        suspend fun getPopularMovies()
    }
}