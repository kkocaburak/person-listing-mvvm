package com.bkarakoca.personlistingmvvm.internal.extension

fun Int?.toSafeInt(): Int {
    return this ?: 0
}