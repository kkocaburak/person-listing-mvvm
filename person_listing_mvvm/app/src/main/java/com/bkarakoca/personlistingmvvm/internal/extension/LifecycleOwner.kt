package com.bkarakoca.personlistingmvvm.internal.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this) {
        it?.let { t -> observer(t) }
    }
}

fun <T> LifecycleOwner.observeNonNull(liveData: LiveData<T>, observer: (t: T) -> Unit) {
    liveData.observe(this) {
        it?.let(observer)
    }
}