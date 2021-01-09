package com.example.dindinapp.viewmodels

import com.airbnb.mvrx.*
import com.airbnb.mvrx.MvRxViewModelFactory
import com.example.dindinapp.models.FoodDeliveryResponse
import com.example.dindinapp.models.FoodFilter
import com.example.dindinapp.models.FoodMenu
import com.example.dindinapp.repository.FoodRepository
import com.example.dindinapp.states.FoodDeliveryState
import io.reactivex.disposables.CompositeDisposable
import org.koin.java.KoinJavaComponent.inject

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.


class FoodDeliveryViewModel(val foodState: FoodDeliveryState,
                            private val foodRepository: FoodRepository):
    BaseMvRxViewModel<FoodDeliveryState>(foodState, debugMode = true) {

    private val categoryMap = HashMap<String, List<FoodFilter>>()
    private val foodMenuMap = HashMap<String, List<FoodMenu>>()


    fun doGetFoodFilters(category: String, foodDeliveryResponse: FoodDeliveryResponse){
        withState {
            Success(getFoodFilters(category, foodDeliveryResponse))
        }
    }


    fun doGetFoodMenuAds(category: String, filter: String, foodDeliveryResponse: FoodDeliveryResponse){
        withState {
            Success(getFoodMenuAds())
        }
    }

    fun doGetFoodMenu(category: String, filter: String, foodDeliveryResponse: FoodDeliveryResponse){
        withState {
            Success(getFoodMenu(category, filter))
        }
    }

    fun doGetFoodCategory(){
        withState {
            foodRepository.requestFood().execute {
                when (it) {
                    is Success -> {
                        copy(foodCategoryList =  Success(it.invoke().categories))
                    }
                    is Fail -> {
                        copy(foodMenuList = Fail(it.error))
                    }
                    else -> {
                        copy()
                    }
                }
            }
        }

    }

    private fun getFoodFilters(category: String, foodDeliveryResponse: FoodDeliveryResponse):  List<FoodFilter>?{

        foodDeliveryResponse.categories.forEach {
            //Categories
            categoryMap[it.name] = it.foodFilter
        }
        return categoryMap[category]
    }



    private fun getFoodMenu(category: String, filter: String):
            List<FoodMenu>?{
        categoryMap[category]?.forEach {
            //Categories
            foodMenuMap[it.name] = it.foodMenus
        }
        return foodMenuMap[filter]
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