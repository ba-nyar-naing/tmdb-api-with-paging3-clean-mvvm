package com.banyar.presentation.detail

import com.banyar.presentation.base.BaseContract

object DetailContract {

    interface IViewModel : BaseContract.IViewModel {
        fun getMovieDetail()
    }

    interface IRepository<VM : IViewModel> : BaseContract.IRepository<VM> {
        suspend fun getMovieDetail()
    }
}