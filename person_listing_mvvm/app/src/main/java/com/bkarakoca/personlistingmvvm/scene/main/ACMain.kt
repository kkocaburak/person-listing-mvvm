package com.bkarakoca.personlistingmvvm.scene.main

import androidx.activity.viewModels
import com.bkarakoca.personlistingmvvm.R
import com.bkarakoca.personlistingmvvm.base.BaseActivity
import com.bkarakoca.personlistingmvvm.databinding.ActivityMainBinding
import com.bkarakoca.personlistingmvvm.internal.extension.hide
import com.bkarakoca.personlistingmvvm.internal.extension.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ACMain : BaseActivity<ActivityMainBinding, ACMainVM>() {

    override val layoutId get() = R.layout.activity_main

    override val viewModel: ACMainVM by viewModels()

    override fun showLoading() {
        binder.progressBarLoading.show()
    }

    override fun hideLoading() {
        binder.progressBarLoading.hide()
    }
}
