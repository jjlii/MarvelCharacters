package com.example.marvelcharacters.di

import android.app.Application
import com.example.marvelcharacters.di.Koin.appModule
import com.example.marvelcharacters.di.Koin.dataModule
import com.example.marvelcharacters.di.Koin.useCaseModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin


class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            AndroidLogger()
            androidContext(this@MainApplication)
            modules(listOf(appModule, dataModule, useCaseModel))
        }
    }
}