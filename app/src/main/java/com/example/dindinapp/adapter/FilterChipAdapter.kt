package com.example.dindinapp.adapter

import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dindinapp.R
import com.example.dindinapp.models.FoodFilter
import com.google.android.material.chip.ChipDrawable

// Created by Gbenga Oladipupo(Devmike01) on 1/9/21.


class FilterChipAdapter : RecyclerView.Adapter<FilterChipAdapter.FilterChipViewHolder>() {

    private lateinit var filterChipList: List<FoodFilter>

    fun submitList(filterChipList: List<FoodFilter>){
        this.filterChipList = filterChipList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterChipViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.chip_item_layout, parent,
            false)
        return  FilterChipViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterChipViewHolder, position: Int) {
        holder.bind(filterChipList[position])
    }

    override fun getItemCount() =  if(this::filterChipList.isInitialized)
    { filterChipList.size }else { 0 }

    class FilterChipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(foodFilter : FoodFilter){
            val filterTv =itemView.findViewById<TextView>(R.id.filter_chip_tv)
            val chipDrawable = ChipDrawable.createFromResource(itemView.context, R.xml.filter_chip)
            chipDrawable.setBounds(0, 0, chipDrawable.intrinsicWidth, chipDrawable.intrinsicHeight)
            filterTv.background =chipDrawable
            filterTv.text = foodFilter.name
        }
    }

}