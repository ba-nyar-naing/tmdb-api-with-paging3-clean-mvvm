package com.banyar.presentation.home

import com.banyar.presentation.base.BaseContract

object HomeContract {

    interface IViewModel : BaseContract.IViewModel {
        fun getPopularMovies()
    }

    interface IRepository<VM : IViewModel> : BaseContract.IRepository<VM> {
        suspend fun getPopularMovies()
    }
}