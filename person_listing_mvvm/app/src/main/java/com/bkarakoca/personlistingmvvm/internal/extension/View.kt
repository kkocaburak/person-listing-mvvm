package com.bkarakoca.personlistingmvvm.internal.extension

import android.view.View

val View.isVisible get() = visibility == View.VISIBLE

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}
