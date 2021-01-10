package com.example.dindinapp.viewmodels

import android.util.Log
import com.airbnb.mvrx.*
import com.airbnb.mvrx.MvRxViewModelFactory
import com.example.dindinapp.models.FoodDeliveryResponse
import com.example.dindinapp.models.FoodFilterResponse
import com.example.dindinapp.models.FoodMenu
import com.example.dindinapp.repository.FoodRepository
import com.example.dindinapp.states.FoodDeliveryState
import io.reactivex.Observable
import org.koin.java.KoinJavaComponent.inject

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.

class FoodDeliveryViewModel(private val foodState: FoodDeliveryState,
                            private val foodRepository: FoodRepository):
    BaseMvRxViewModel<FoodDeliveryState>(foodState, debugMode = true) {

    private val categoryMap = HashMap<String, List<FoodFilterResponse>>()
    private val foodMenuMap = HashMap<String, List<FoodMenu>>()

    init {
        doGetFoodCategory(null)
    }


    fun doGetFoodCategory(category: String?){
        withState {
            foodRepository.requestFood().execute {
                when (it) {
                    is Success -> {
                        Log.d("MainActivity", "Food list > ${it.invoke().categoryResponses}")
                        getFoodFilters(it.invoke())
                        val foodList = ArrayList<FoodMenu>()
                        val foodAdList =  ArrayList<String>()
                        val mFoodFilter = ArrayList<FoodFilterResponse>()

                        if(category != null) {
                            categoryMap[category]?.forEach { foodFilter ->
                                mFoodFilter.add(foodFilter)
                                foodList.addAll(foodFilter.foodMenus)
                                foodAdList.addAll(foodFilter.foodMenus.filter { foodMenu ->  foodMenu.isPromo}.map { data -> data.image })
                            }
                        }
                        copy(foodCategoryResponseList =  Success(it.invoke().categoryResponses),
                            foodMenuList = foodList, foodFilter =mFoodFilter, foodAdList = foodAdList)
                    }
                    is Fail -> {
                        copy(foodCategoryResponseList = Fail(it.error))
                    }
                    else -> {
                        copy()
                    }
                }
            }
        }

    }

    private fun getFoodFilters(foodDeliveryResponse: FoodDeliveryResponse){
        foodDeliveryResponse.categoryResponses.forEach {

            categoryMap[it.name] = it.foodFilterResponse
        }
    }



    private fun getFoodMenuAds(){
        val foodAdList = mutableListOf<FoodMenu>()
        foodMenuMap.forEach {
            foodAdList.addAll(it.value.filter{foodMenu -> foodMenu.isPromo})
        }
    }

    companion object : MvRxViewModelFactory<FoodDeliveryViewModel, FoodDeliveryState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: FoodDeliveryState): FoodDeliveryViewModel? {
            val watchlistRepository  by inject<FoodRepository>(FoodRepository::class.java)
            return FoodDeliveryViewModel(state, watchlistRepository)
        }
    }


}