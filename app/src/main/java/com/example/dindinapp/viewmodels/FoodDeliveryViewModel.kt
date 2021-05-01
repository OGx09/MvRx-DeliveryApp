package com.example.dindinapp.viewmodels

import android.util.Log
import com.airbnb.mvrx.*
import com.airbnb.mvrx.MvRxViewModelFactory
import com.example.dindinapp.models.CategoryResponse
import com.example.dindinapp.models.FoodDeliveryResponse
import com.example.dindinapp.models.FoodFilterResponse
import com.example.dindinapp.models.FoodMenu
import com.example.dindinapp.repository.FoodRepository
import com.example.dindinapp.states.FoodDeliveryState
import org.koin.java.KoinJavaComponent.inject
import java.lang.Exception

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.

class FoodDeliveryViewModel(private val foodState: FoodDeliveryState,
                            private val foodRepository: FoodRepository):
    BaseMvRxViewModel<FoodDeliveryState>(foodState, debugMode = false) {

    private val categoryMap = HashMap<String, List<FoodFilterResponse>>()
    private val mFoodMenuList = ArrayList<FoodMenu>()

    init {
        getStarred()
    }

    fun getStarred(){
        withState {
            foodRepository.getStarred().execute {
                when(it){
                    is Success ->{
                        Log.d("FoodDeliveryViewModel", "${it.invoke()}")
                        copy(starredResponse = Success(it.invoke()))
                    } is Fail ->{
                    Log.d("FoodDeliveryViewModel", "ERROR _> ${it.error}")
                    copy(starredResponse = Fail(it.error))
                    }else -> {
                        copy()
                    }
                }
            }
        }
    }

    fun doGetFoodCategory(category: String?){
        withState {
            foodRepository.requestFood()
                .execute {
                when (it) {
                    is Success -> {
                        getFoodFilters(it.invoke())
                        val foodList = ArrayList<FoodMenu>()
                        val foodAdList =  ArrayList<String>()
                        val mFoodFilter = ArrayList<FoodFilterResponse>()
                        var result : Async<ArrayList<CategoryResponse>>? = null

                        if(category != null) {
                            if(categoryMap[category].isNullOrEmpty()){
                                result = Fail(Exception("$category Category not found"))
                            }else{
                                categoryMap[category]?.forEach { foodFilter ->
                                    mFoodFilter.add(foodFilter)
                                    foodList.addAll(foodFilter.foodMenus)
                                    foodAdList.addAll(foodFilter.foodMenus.filter { foodMenu ->  foodMenu.isPromo}.map { data -> data.image })
                                }
                            }
                        }
                        copy(foodCategoryResponseList = result ?: Success(it.invoke().categoryResponses),
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

    fun setSelectedOrders(foodMenu: FoodMenu){
        mFoodMenuList.add(foodMenu)
    }


    companion object : MvRxViewModelFactory<FoodDeliveryViewModel, FoodDeliveryState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: FoodDeliveryState): FoodDeliveryViewModel? {
            val watchlistRepository  by inject<FoodRepository>(FoodRepository::class.java)
            return FoodDeliveryViewModel(state, watchlistRepository)
        }
    }


}