package com.bkarakoca.personlistingmvvm.internal.extension

import androidx.lifecycle.viewModelScope
import com.bkarakoca.personlistingmvvm.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun BaseViewModel.launch(block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch ( exceptionHandler, CoroutineStart.DEFAULT, block)
}