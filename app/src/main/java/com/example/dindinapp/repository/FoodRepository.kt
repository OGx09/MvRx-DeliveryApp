package com.example.dindinapp.repository

import com.example.dindinapp.models.Category
import com.example.dindinapp.repository.network.FoodService
import io.reactivex.Observable

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.


class FoodRepository(private val foodService: FoodService) {

    fun requestFood(): Observable<List<Category>> {
        return foodService.getUsers()
    }
}