package com.example.dindinapp.repository.network

import com.example.dindinapp.models.Category
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.http.GET


// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.



interface FoodService {

    @GET("/categories")
    fun getUsers(): Observable<List<Category>>
}