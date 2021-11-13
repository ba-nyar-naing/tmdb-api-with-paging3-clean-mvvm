package com.banyar.presentation.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment(), BaseContract.IView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUIElements()
        setupObserver()
        setupActionListener()
    }

    override fun setActionBarTitle(title: String?) {
        when (val activity = requireActivity()) {
            is BaseActivity -> {
                activity.supportActionBar?.title = title
            }
        }
    }
}