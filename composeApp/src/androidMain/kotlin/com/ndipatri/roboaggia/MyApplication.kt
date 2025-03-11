package com.ndipatri.roboaggia

import android.app.Application
import app.rive.runtime.kotlin.core.Rive
import di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApplication)
        }

        Rive.init(this@MyApplication)
    }
}