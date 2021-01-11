package com.example.dindinapp.adapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.dindinapp.features.AdsFragment


// Created by Gbenga Oladipupo(Devmike01) on 1/8/21.


class TopAdViewPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private lateinit var advertList : List<String>

    fun addAdverts(advertList : List<String>){
        this.advertList = advertList
        notifyDataSetChanged()
    }

    override fun getItem(i: Int): Fragment {
        val fragment: Fragment = AdsFragment()
        val args = Bundle()
        // Our object is just an integer :-P
        args.putString(AdsFragment.ARG_ADS_IMAGE, advertList[i])
        fragment.arguments = args
        return fragment
    }

    override fun getCount() = if (this::advertList.isInitialized){ advertList.size }else {
        0
    }

}
