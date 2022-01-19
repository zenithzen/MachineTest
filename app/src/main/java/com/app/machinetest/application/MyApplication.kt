package com.app.machinetest.application

import android.app.Application
import com.app.machinetest.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree


@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
