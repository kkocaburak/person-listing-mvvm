package com.bkarakoca.personlistingmvvm.internal.util

fun <T> lazyThreadSafetyNone(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)