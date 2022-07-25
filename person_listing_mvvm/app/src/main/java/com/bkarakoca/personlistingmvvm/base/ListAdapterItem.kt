package com.bkarakoca.personlistingmvvm.base

interface ListAdapterItem {
    val id: Long?

    override fun equals(other: Any?): Boolean
}
