package com.example.dindinapp.features

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.dindinapp.R


// Created by Gbenga Oladipupo(Devmike01) on 1/9/21.


class AdsFragment : Fragment() {

     companion object {
         const val ARG_ADS_IMAGE = "AdsFragment.ARG_ADS_IMAGE"
     }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("AdsFragment@1", "ARGUMENTS $arguments")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ads_item_layout, container, false)
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        Log.d("AdsFragment", "ARGUMENTS $arguments")
        arguments?.apply {
            Glide.with(requireActivity()).load(this.getString(ARG_ADS_IMAGE))
                .placeholder(R.drawable.ic_baseline_mood_bad_24)
                .fallback(R.drawable.ic_baseline_mood_bad_24)
                .into(view.findViewById(R.id.ads_iv) as ImageView)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("AdsFragment ->", "ARGUMENTS $arguments")
        arguments?.apply {
            Glide.with(requireActivity()).load(this.getString(ARG_ADS_IMAGE))
                .placeholder(R.drawable.ic_baseline_mood_bad_24)
                .fallback(R.drawable.ic_baseline_mood_bad_24)
                .into(view?.findViewById(R.id.ads_iv) as ImageView)
        }
    }
}