package com.example.dindinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import com.airbnb.mvrx.MvRxView
import com.airbnb.mvrx.activityViewModel
import com.example.dindinapp.adapter.TopAdViewPagerAdapter
import com.example.dindinapp.databinding.ActivityMainBinding
import com.example.dindinapp.viewmodels.FoodDeliveryViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val topAdsAdapter: TopAdViewPagerAdapter by inject()
    private val foodDeliveryViewModel: FoodDeliveryViewModel by inject()

    private lateinit var mainActivityBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainActivityBinding.root
        setContentView(view)

        //val inclView =
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}