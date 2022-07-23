package com.bkarakoca.personlistingmvvm.base

interface ListAdapterItem {
    val id: Long?
    val layoutId: Int

    override fun equals(other: Any?): Boolean
}
