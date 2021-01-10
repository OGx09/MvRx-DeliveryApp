package com.example.dindinapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dindinapp.R
import com.example.dindinapp.models.FoodFilterResponse
import com.google.android.material.chip.ChipDrawable

// Created by Gbenga Oladipupo(Devmike01) on 1/9/21.


class FilterChipAdapter : RecyclerView.Adapter<FilterChipAdapter.FilterChipViewHolder>() {

    private lateinit var filterResponseChipList: List<FoodFilterResponse>

    fun submitList(filterResponseChipList: List<FoodFilterResponse>){
        this.filterResponseChipList = filterResponseChipList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterChipViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.chip_item_layout, parent,
            false)
        return  FilterChipViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterChipViewHolder, position: Int) {
        holder.bind(filterResponseChipList[position])
    }

    override fun getItemCount() =  if(this::filterResponseChipList.isInitialized)
    { filterResponseChipList.size }else { 0 }

    class FilterChipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(foodFilterResponse : FoodFilterResponse){
            val filterTv =itemView.findViewById<TextView>(R.id.filter_chip_tv)
            filterTv.text = foodFilterResponse.name
        }
    }

}