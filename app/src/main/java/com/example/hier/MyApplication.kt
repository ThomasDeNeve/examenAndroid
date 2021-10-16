package com.example.hier

import android.app.Application
import com.example.hier.dependency_injection.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application(){
    override fun onCreate(){
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(networkModule)
        }
    }
}