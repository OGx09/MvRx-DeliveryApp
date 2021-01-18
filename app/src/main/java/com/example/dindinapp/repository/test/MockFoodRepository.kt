package com.example.dindinapp.repository.test

import android.content.Context
import com.example.dindinapp.models.FoodDeliveryResponse
import com.example.dindinapp.repository.FoodRepository
import com.example.dindinapp.repository.network.FoodService
import com.google.gson.Gson
import io.reactivex.Observable

// Created by Gbenga Oladipupo(Devmike01) on 1/12/21.


open class MockFoodRepository(private val foodService: FoodService, val context: Context) : FoodRepository(foodService) {

    override fun requestFood(): Observable<FoodDeliveryResponse> {
        val response =  RestServiceMockUtils.getStringFromFile(context.applicationContext, "food_delivery.json")
        val foodDeliveryResponse = Gson().fromJson(response, FoodDeliveryResponse::class.java)
        return Observable.just(foodDeliveryResponse)
    }

}