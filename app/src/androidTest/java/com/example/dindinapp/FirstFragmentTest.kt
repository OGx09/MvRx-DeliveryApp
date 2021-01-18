package com.example.dindinapp

import android.view.View
import androidx.core.content.MimeTypeFilter.matches
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.dindinapp.features.FirstFragment
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


// Created by Gbenga Oladipupo(Devmike01) on 1/15/21.

@LargeTest
@RunWith(AndroidJUnit4::class)
class FirstFragmentTest {

    @Before
    fun init(){

        val scenario = launchFragmentInContainer<FirstFragment>()

    }

    @Test
    fun testAppBarLayout(){
        onView(withId(R.id.appbar))
            .perform(CustomViewActions.collapseAppBarLayout())
            .check(ViewAssertions.doesNotExist())
    }


}