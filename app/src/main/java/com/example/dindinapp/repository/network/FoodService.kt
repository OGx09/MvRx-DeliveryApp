package com.example.dindinapp.repository.network

import com.example.dindinapp.models.FoodDeliveryResponse
import com.example.dindinapp.models.StarredResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.



interface FoodService {

    // /v0/b/triviabillionia.appspot.com/o/food_delivery.json?alt=media&token=62ae9400-39cf-4023-85e4-6c7c6a8a2d81
    @GET("/v0/b/ridebryte.appspot.com/o/food_delivery.json?alt=media&token=1cdde65d-a82c-411e-89f9-9bd48627a428")
    fun getFoodCategories(@Query("token") token: String): Observable<FoodDeliveryResponse>

    @GET("/starred")
    fun getStarred(): Observable<List<StarredResponse>>
}