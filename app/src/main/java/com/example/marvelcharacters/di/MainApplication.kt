package com.example.marvelcharacters.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin


class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin{
            AndroidLogger()
            androidContext(this@MainApplication)
            listOf(
                Koin.appModule,
                Koin.dataModule,
                Koin.useCaseModel
            )
        }
    }
}