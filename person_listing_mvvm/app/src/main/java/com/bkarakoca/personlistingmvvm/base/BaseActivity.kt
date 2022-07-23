package com.bkarakoca.personlistingmvvm.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bkarakoca.personlistingmvvm.internal.util.lazyThreadSafetyNone

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    @get:LayoutRes
    abstract val layoutId: Int

    protected abstract val viewModel: VM

    val binder by lazyThreadSafetyNone<B> {
        DataBindingUtil.setContentView(this, layoutId)
    }
}