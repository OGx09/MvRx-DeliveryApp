package com.example.dindinapp.states

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.example.dindinapp.models.Category

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.


data class FoodDeliveryState(val foodList: Async<List<Category>> = Uninitialized): MvRxState
