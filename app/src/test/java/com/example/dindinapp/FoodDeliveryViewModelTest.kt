package com.example.dindinapp

import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.airbnb.mvrx.*
import com.example.dindinapp.di.appModule
import com.example.dindinapp.models.CategoryResponse
import com.example.dindinapp.repository.network.FoodService
import com.example.dindinapp.repository.test.MockFoodRepository
import com.example.dindinapp.states.FoodDeliveryState
import com.example.dindinapp.viewmodels.FoodDeliveryViewModel
import junit.framework.Assert.assertNotNull
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.Mockito.*
import org.robolectric.annotation.Config

// Created by Gbenga Oladipupo(Devmike01) on 1/11/21.
@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class FoodDeliveryViewModelTest {

    var foodDeliveryViewModel: FoodDeliveryViewModel? = null

    @Before
    fun init(){
        val foodState = FoodDeliveryState()
        val repository = MockFoodRepository(mock(FoodService::class.java), ApplicationProvider.getApplicationContext<FoodApp>())
        foodDeliveryViewModel = FoodDeliveryViewModel(foodState, repository)
    }

    @Test
    fun test_FoodDeliveryModel(){
        assertNotNull(foodDeliveryViewModel)
    }


    @Test
    fun test_GetFoodDeliveryUninitialized(){
        //foodDeliveryViewModel?.doGetFoodCategory("Sushi")
        foodDeliveryViewModel?.apply {
            withState(this){
                if(it.foodCategoryResponseList is Uninitialized){
                    Assert.assertEquals(it.foodCategoryResponseList, Uninitialized)
                    return@withState
                }
                Assert.fail("$it is not initialized!")
               // Assert.fail("Not Initialized!")
            }
        }
    }

    @Test
    fun test_GetFoodDeliverySuccess(){
        foodDeliveryViewModel?.doGetFoodCategory(null)
        Thread.sleep(2000)
        foodDeliveryViewModel?.apply {
            withState(this){
                if(it.foodCategoryResponseList is Success) {
                    Assert.assertEquals(it.foodCategoryResponseList.invoke()?.first()?.name, "Sushi")
                    return@withState
                }
                Assert.fail("it.foodCategoryResponseList is not successful")
            }
        }
    }


    @Test
    fun test_GetFoodDeliveryFailed(){
        foodDeliveryViewModel?.doGetFoodCategory("111")
        Thread.sleep(2000)
        foodDeliveryViewModel?.apply {
            withState(this){
                if(it.foodCategoryResponseList is Fail) {
                    val exception = it.foodCategoryResponseList
                    Assert.assertEquals((exception as Fail<ArrayList<CategoryResponse>>).error.message,
                        "111 Category not found")
                }
            }
        }
    }


    @After
    fun tearDown(){
        stopKoin()
    }
}
