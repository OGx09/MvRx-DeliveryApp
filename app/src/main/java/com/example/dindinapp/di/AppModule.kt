package com.example.dindinapp.di

import androidx.fragment.app.FragmentActivity
import com.example.dindinapp.adapter.FilterChipAdapter
import com.example.dindinapp.adapter.FoodAdapter
import com.example.dindinapp.adapter.TopAdViewPagerAdapter
import com.example.dindinapp.adapter.TopMenuAdapter
import com.example.dindinapp.repository.FoodRepository
import com.example.dindinapp.repository.network.FoodService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.

const val BASE_URL = "https://firebasestorage.googleapis.com"

fun provideRetrofit(): Retrofit {

    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}

fun provideFoodService(retrofit: Retrofit):
        FoodService = retrofit.create(FoodService::class.java)


val appModule = module {
    factory { provideRetrofit() }
    single { provideFoodService(get()) }
    single { FoodRepository(get()) }
    factory { FoodAdapter() }
    factory { FilterChipAdapter() }
    factory { TopMenuAdapter() }
    single { TopAdViewPagerAdapter(get()) }
}