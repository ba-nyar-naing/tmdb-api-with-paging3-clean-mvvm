package com.banyar.presentation.ui.base

object BaseContract {

    interface IView {
        fun setupUIElements()
        fun setupObserver()
        fun setupActionListener()

        fun setActionBarTitle(title: String?)
    }

    interface IViewModel {
        fun init()
    }
}