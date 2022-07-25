package com.bkarakoca.personlistingmvvm.data.uimodel

import com.bkarakoca.personlistingmvvm.base.ListAdapterItem

data class PersonUIModel(
    val personNameWithId: String,
    val personId: Int
) : ListAdapterItem {
    override val id: Long = personId.toLong()
}