package com.example.twitterapp.bases

import android.app.Application
import com.example.twitterapp.di.networkModule
import com.example.twitterapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TwitterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TwitterApplication)
            modules(listOf(networkModule, viewModelModule))
        }
    }
}