package com.bkarakoca.personlistingmvvm.internal.extension

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.notifyDataChange() {
    this.value = this.value
}