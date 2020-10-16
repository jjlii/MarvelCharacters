package com.example.marvelcharacters.di

import android.app.Application
import org.koin.android.ext.android.startKoin


class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this,
        listOf(
            Koin.appModule,
            Koin.dataModule,
            Koin.usecasesModule
        ))
    }
}