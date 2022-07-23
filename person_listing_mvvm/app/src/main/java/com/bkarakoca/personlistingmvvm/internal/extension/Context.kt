package com.bkarakoca.personlistingmvvm.internal.extension

import android.content.Context
import android.widget.Toast
import com.bkarakoca.personlistingmvvm.internal.popup.Popup
import com.bkarakoca.personlistingmvvm.internal.popup.PopupListener
import com.bkarakoca.personlistingmvvm.internal.popup.PopupModel

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun Context.showPopup(model: PopupModel, listener: PopupListener? = null) {
    Popup(this, model, listener).show()
}
