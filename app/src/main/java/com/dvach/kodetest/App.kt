package com.dvach.kodetest

import android.app.Application
import com.dvach.kodetest.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(org.koin.core.logger.Level.DEBUG)
            androidContext(this@App)
            modules(listOf(viewModelModule, retrofitModule, apiModule, modelModule, dataBaseModule))
        }
    }
}