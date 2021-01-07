package com.example.dindinapp

import android.app.Application
import com.example.dindinapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.


class FoodApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FoodApp)
            modules(appModule)
        }
    }
}