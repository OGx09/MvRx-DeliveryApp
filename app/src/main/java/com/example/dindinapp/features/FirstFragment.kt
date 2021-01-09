package com.example.dindinapp.features

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import com.example.dindinapp.R
import com.example.dindinapp.viewmodels.FoodDeliveryViewModel
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment :BaseMvRxFragment(){

    //val factory = FoodDeliveryViewModel
    private val foodDeliveryViewModel: FoodDeliveryViewModel by activityViewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      foodDeliveryViewModel.doGetFoodCategory()
    }


    override fun invalidate() {
        withState(foodDeliveryViewModel){state ->
            Log.d("MainActivity", "Food list -> ${state.foodCategoryList}}")
            when(state.foodCategoryList){
                is Loading ->{
                    Log.d("MainActivity", "Food list -> LOADING")
                }
                is Success ->{
                    val firstCategory = state.foodCategoryList.invoke().first()
                    val foodFilter = firstCategory.foodFilter
                    foodDeliveryViewModel.doGetFoodMenu(firstCategory.name, foodFilter[0].name)
                    Log.d("MainActivity", "Food list -> ${state.foodList}")
                }

                is Fail ->{
                    Snackbar.make(requireActivity().window.decorView, "An error has occurred", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }else ->{

                }
            }

            when(state.foodMenuList){
                is Loading ->{
                    Log.d("MainActivity", "Food list -> LOADING")
                }
                is Success ->{
                    Log.d("MainActivity", "Food list -> ${state.foodList}")
                }

                is Fail ->{
                    Snackbar.make(requireActivity().window.decorView, "An error has occurred", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }else ->{

            }
            }
        }
    }

}