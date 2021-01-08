package com.example.dindinapp.viewmodels

import android.util.Log
import com.airbnb.mvrx.*
import com.airbnb.mvrx.MvRxViewModelFactory
import com.example.dindinapp.repository.FoodRepository
import com.example.dindinapp.repository.network.FoodService
import com.example.dindinapp.states.FoodDeliveryState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

// Created by Gbenga Oladipupo(Devmike01) on 1/7/21.


class FoodDeliveryViewModel(val foodState: FoodDeliveryState,
                            private val foodRepository: FoodRepository):
    BaseMvRxViewModel<FoodDeliveryState>(foodState, debugMode = true) {

    private val compositeDisposable = CompositeDisposable()


    fun doGetFood(){
        withState {
            foodRepository.requestFood().execute {
                when (it) {
                    is Success -> {
                        copy(foodList = Success(it.invoke().categories))
                    }
                    is Fail -> {
                        copy(foodList = Fail(it.error))
                    }
                    else -> {
                        copy()
                    }
                }
            }
        }

    }

    companion object : MvRxViewModelFactory<FoodDeliveryViewModel, FoodDeliveryState> {
        override fun create(viewModelContext: ViewModelContext,
                            state: FoodDeliveryState): FoodDeliveryViewModel? {
            val watchlistRepository  by inject<FoodRepository>(FoodRepository::class.java)
            return FoodDeliveryViewModel(state, watchlistRepository)
        }
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}