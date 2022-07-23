package com.bkarakoca.personlistingmvvm.internal.popup

class PopupListener(
    val onPositiveButtonClick: (() -> Unit)? = null,
    val onNegativeButtonClick: (() -> Unit)? = null
)
