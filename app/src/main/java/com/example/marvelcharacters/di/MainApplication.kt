package com.example.marvelcharacters.di

import android.app.Application
import org.koin.android.ext.android.startKoin


class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        val koin = Koin()
        startKoin(this,
        listOf(
            koin.appModule,
            koin.dataModule,
            koin.usecasesModule
        ))
    }
}