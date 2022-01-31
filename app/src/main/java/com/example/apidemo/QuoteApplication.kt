package com.example.apidemo

import android.app.Application
import com.example.apidemo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

open class QuoteApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@QuoteApplication)
            val modules = mutableListOf<Module>().apply {
                addAll(appModule)
            }
            modules(modules)
        }
    }

}