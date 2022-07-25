package com.bkarakoca.personlistingmvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.bkarakoca.personlistingmvvm.BR
import com.bkarakoca.personlistingmvvm.internal.extension.observeNonNull
import com.bkarakoca.personlistingmvvm.internal.extension.showPopup
import com.bkarakoca.personlistingmvvm.navigation.NavigationCommand

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> :
    Fragment() {

    lateinit var binder: B

    @get:LayoutRes
    abstract val layoutId: Int

    protected abstract val viewModel: VM

    abstract fun initialize()

    abstract fun setListeners()

    abstract fun setReceivers()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binder.lifecycleOwner = viewLifecycleOwner
        binder.setVariable(BR.viewModel, viewModel)

        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLoading()
        observeNavigation()
        observeFailure()

        initialize()
        setListeners()
        setReceivers()
    }

    private fun observeLoading() {
        observeNonNull(viewModel.loading) { shouldLoading ->
            if (shouldLoading) {
                (requireActivity() as? BaseActivity<*, *>)?.showLoading()
            } else {
                (requireActivity() as? BaseActivity<*, *>)?.hideLoading()
            }
        }
    }

    private fun observeNavigation() {
        observeNonNull(viewModel.navigation) {
            it.getContentIfNotHandled()?.let { command ->
                handleNavigation(command)
            }
        }
    }

    private fun observeFailure() {
        observeNonNull(viewModel.popup) {
            it.getContentIfNotHandled()?.let { popupModel ->
                requireContext().showPopup(popupModel)
            }
        }
    }

    open fun handleNavigation(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.ToDirection -> {
                findNavController().navigate(command.directions, getExtras())
            }
            is NavigationCommand.Popup -> {
                with(command) {
                    context?.showPopup(model, listener)
                }
            }
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()
}
