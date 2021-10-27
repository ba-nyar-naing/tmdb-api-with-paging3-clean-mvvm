package com.banyar.presentation.base

import javax.inject.Inject

open class BaseRepository<VM : BaseContract.IViewModel>
@Inject constructor() : BaseContract.IRepository<VM> {

    private lateinit var viewModel: VM

    override val vm: VM
        get() = viewModel

    override fun onAttach(viewModel: VM) {
        this.viewModel = viewModel
    }
}