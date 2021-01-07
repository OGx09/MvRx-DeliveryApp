package com.example.dindinapp.states

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.example.dindinapp.models.Category

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.


data class FoodDeliveryState(val users: Async<List<Category>>): MavericksState
