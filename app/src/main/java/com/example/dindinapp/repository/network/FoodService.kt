package com.example.dindinapp.repository.network

import com.example.dindinapp.models.Category
import com.example.dindinapp.models.FoodDeliveryResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.http.GET
import retrofit2.http.Query


// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.



interface FoodService {

    // /v0/b/triviabillionia.appspot.com/o/food_delivery.json?alt=media&token=62ae9400-39cf-4023-85e4-6c7c6a8a2d81
    @GET("/v0/b/ridebryte.appspot.com/o/food_delivery.json?alt=media&token=929a9708-5219-4b6b-9da5-55f9d160754f")
    fun getFoodCategories(@Query("token") token: String): Observable<FoodDeliveryResponse>
}