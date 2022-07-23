package com.bkarakoca.personlistingmvvm.scene.first

import androidx.fragment.app.viewModels
import com.bkarakoca.personlistingmvvm.R
import com.bkarakoca.personlistingmvvm.base.BaseFragment
import com.bkarakoca.personlistingmvvm.databinding.FragmentFirstPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FRFirstPage : BaseFragment<FRFirstPageVM, FragmentFirstPageBinding>() {
    override val layoutId: Int get() = R.layout.fragment_first_page

    override val viewModel: FRFirstPageVM by viewModels()

    override fun initialize() {}

    override fun setListeners() {}

    override fun setReceivers() {}
}