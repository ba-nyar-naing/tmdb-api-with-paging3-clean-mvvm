package com.banyar.presentation.base

import androidx.lifecycle.ViewModel
import timber.log.Timber

abstract class BaseViewModel : ViewModel(), BaseContract.IViewModel {

    override fun onTest(message: String) {
        Timber.d("onTest: $message")
    }
}