package com.example.placeholder

import android.app.Application
import com.example.placeholder.common.NetworkModule
import com.example.placeholder.home.HomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupTimber()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                NetworkModule.create(),
                HomeModule.create()
            )
        }
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}