package com.bkarakoca.personlistingmvvm.application

import android.app.Application
import com.bkarakoca.personlistingmvvm.internal.util.NetworkStateHolder.registerConnectivityMonitor
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        registerConnectivityMonitor()
    }
}