package com.banyar.presentation.ui.detail

import com.banyar.presentation.ui.base.BaseContract

object DetailContract {

    interface IViewModel : BaseContract.IViewModel {
        fun getMovieDetail()
    }

    interface IRepository<VM : IViewModel> : BaseContract.IRepository<VM> {
        suspend fun getMovieDetail()
    }
}