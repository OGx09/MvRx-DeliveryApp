package com.example.dindinapp.repository

import com.example.dindinapp.models.FoodDeliveryResponse
import com.example.dindinapp.repository.network.FoodService
import io.reactivex.Observable

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.


open class FoodRepository(private val foodService: FoodService) {

    open fun requestFood(): Observable<FoodDeliveryResponse> {

        return foodService.getFoodCategories("f26ef1b5-e4ba-4396-8eca-ebd319a6eb6c")
    }
}