package com.example.dindinapp

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.dindinapp.features.FoodCategoryFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


// Created by Gbenga Oladipupo(Devmike01) on 1/15/21.

@LargeTest
@RunWith(AndroidJUnit4::class)
class FoodCategoryFragmentTest {

    val scenario = launchFragmentInContainer<FoodCategoryFragment>()

    @Before
    fun init(){
    }

    @Test
    fun testAppBarLayout(){
        onView(withId(R.id.appbar))
            .perform(CustomViewActions.collapseAppBarLayout())
            .check(ViewAssertions.doesNotExist())
    }


}