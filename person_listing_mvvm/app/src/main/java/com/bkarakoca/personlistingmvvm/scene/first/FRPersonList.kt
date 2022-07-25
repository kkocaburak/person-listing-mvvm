package com.bkarakoca.personlistingmvvm.scene.first

import androidx.fragment.app.viewModels
import com.bkarakoca.personlistingmvvm.R
import com.bkarakoca.personlistingmvvm.base.BaseFragment
import com.bkarakoca.personlistingmvvm.databinding.FragmentPersonListBinding
import com.bkarakoca.personlistingmvvm.internal.extension.observeNonNull
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FRPersonList : BaseFragment<FRPersonListVM, FragmentPersonListBinding>() {

    override val layoutId: Int get() = R.layout.fragment_person_list

    override val viewModel: FRPersonListVM by viewModels()

    @Inject
    lateinit var personAdapter: PersonAdapter

    override fun initialize() {
        viewModel.initVM()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binder.recyclerViewPerson.apply {
            adapter = personAdapter
            setHasFixedSize(true)
        }
    }

    override fun setListeners() {
        binder.recyclerViewPerson.addOnScrollListener(
            object : PersonListOnScrollListener() {
                override fun isLoading(): Boolean {
                    return viewModel.isPersonListLoading.value == true
                }

                override fun loadMoreItems() {
                    viewModel.fetchPersonList()
                }
            }
        )
    }

    override fun setReceivers() {
        observeNonNull(viewModel.personList) { personList ->
            personAdapter.submitList(personList)
        }
    }
}