package com.example.dindinapp.states

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.PersistState
import com.airbnb.mvrx.Uninitialized
import com.example.dindinapp.models.CategoryResponse
import com.example.dindinapp.models.FoodFilterResponse
import com.example.dindinapp.models.FoodMenu
import com.example.dindinapp.models.StarredResponse

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.


data class FoodDeliveryState(val foodCategoryResponseList:
                              Async<ArrayList<CategoryResponse>> = Uninitialized,
                             val starredResponse: Async<List<StarredResponse>> = Uninitialized,
                             val foodFilter: List<FoodFilterResponse> = emptyList(),
                             val foodAdList: List<String>? = emptyList(),
                             val foodMenuList: List<FoodMenu>? = emptyList()): MvRxState
