package com.example.dindinapp.states

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.example.dindinapp.models.Category
import com.example.dindinapp.models.FoodMenu

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.


data class FoodDeliveryState(val foodCategoryList: Async<List<Category>> = Uninitialized,
                             val foodMenuList: Async<List<FoodMenu>> = Uninitialized): MvRxState
