package com.banyar.presentation.ui.base

object BaseContract {

    interface IView {
        fun setupUIElements()
        fun setupObserver()
        fun setupActionListener()
    }

    interface IViewModel {
        fun onTest(message: String)
    }

    interface IRepository<VM : IViewModel> {
        val vm: VM

        fun onAttach(viewModel: VM)
    }
}