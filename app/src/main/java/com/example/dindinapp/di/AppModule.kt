package com.example.dindinapp.di

import androidx.fragment.app.FragmentActivity
import com.example.dindinapp.adapter.*
import com.example.dindinapp.mocks.MockInterceptor
import com.example.dindinapp.repository.FoodRepository
import com.example.dindinapp.repository.network.FoodService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.

const val BASE_URL = "https://firebasestorage.googleapis.com"

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()
}

fun provideOkClient(okInterceptor: Interceptor): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(okInterceptor)
        .build()

fun provideInterceptor(): Interceptor = MockInterceptor()

fun provideFoodService(retrofit: Retrofit):
        FoodService = retrofit.create(FoodService::class.java)


val appModule = module {
    single { provideInterceptor() }
    single { provideOkClient(get()) }
    factory { provideRetrofit(get()) }
    single { provideFoodService(get()) }
    single { FoodRepository(get()) }
    factory { FoodAdapter() }
    factory { FilterChipAdapter() }
    factory { TopMenuAdapter() }
    single { TopAdViewPagerAdapter(get()) }
    single { DeliveryItemAdapter() }
}