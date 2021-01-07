package com.example.dindinapp.viewmodels

import com.airbnb.mvrx.MavericksViewModel
import com.example.dindinapp.repository.FoodRepository
import com.example.dindinapp.repository.network.FoodService
import com.example.dindinapp.states.FoodDeliveryState

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.


class FoodDeliveryViewModel(private val foodState: FoodDeliveryState,
                            private val foodService: FoodService):
    MavericksViewModel<FoodDeliveryState>(foodState) {

        fun executeGetFood(){
            foodService.getUsers()
        }
}