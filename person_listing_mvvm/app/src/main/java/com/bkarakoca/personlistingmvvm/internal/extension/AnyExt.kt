package com.bkarakoca.personlistingmvvm.internal.extension

fun Any?.toSafeString(): String {
    return this?.toString() ?: ""
}