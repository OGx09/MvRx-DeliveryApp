package com.example.dindinapp

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import com.google.android.material.appbar.AppBarLayout
import org.hamcrest.Matcher

// Created by Gbenga Oladipupo(Devmike01) on 1/15/21.


object CustomViewActions {

    fun collapseAppBarLayout(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(AppBarLayout::class.java)
            }

            override fun getDescription(): String {
                return "Collapse App Bar Layout"
            }

            override fun perform(uiController: UiController, view: View) {
                val appBarLayout = view as AppBarLayout
                appBarLayout.setExpanded(false)
                uiController.loopMainThreadUntilIdle()
            }
        }
    }
}