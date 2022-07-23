package com.bkarakoca.personlistingmvvm.internal.extension

fun Double?.toSafeDouble(): Double {
    return this ?: 0.0
}