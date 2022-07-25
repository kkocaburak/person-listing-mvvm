package com.bkarakoca.personlistingmvvm.scene.first

import com.bkarakoca.personlistingmvvm.R
import com.bkarakoca.personlistingmvvm.base.BaseBindingAdapter
import com.bkarakoca.personlistingmvvm.data.uimodel.PersonUIModel
import javax.inject.Inject

class PersonAdapter @Inject constructor(): BaseBindingAdapter<PersonUIModel>() {
    override fun getItemViewType(position: Int): Int {
        return R.layout.item_person
    }
}