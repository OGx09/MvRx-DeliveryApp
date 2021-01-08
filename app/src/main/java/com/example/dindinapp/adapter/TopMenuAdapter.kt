package com.example.dindinapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dindinapp.R
import com.example.dindinapp.models.Category
import com.example.dindinapp.models.FoodMenu

// Created by Gbenga Oladipupo(Devmike01) on 1/8/21.


class TopMenuAdapter: RecyclerView.Adapter<TopMenuAdapter.TopMenuViewHolder>() {

    interface OnClickTopMenuListener{
        fun onClickTopMenu(category: Category)
    }

    private lateinit var onClickTopMenuListener: OnClickTopMenuListener

    fun setOnClickTopMenuListener(onClickTopMenuListener: OnClickTopMenuListener){
        this.onClickTopMenuListener = onClickTopMenuListener
    }

    private val categoryList = ArrayList<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMenuViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.top_menu_item_layout, parent, false)
        return TopMenuViewHolder(view, onClickTopMenuListener)
    }

    override fun onBindViewHolder(holder: TopMenuViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }


    class TopMenuViewHolder(private val view: View, private val onClickTopMenuListener: OnClickTopMenuListener) : RecyclerView.ViewHolder(view){

        fun bind(category: Category){
            val menuTitleTv = view.findViewById<TextView>(R.id.menu_title_tv)
            menuTitleTv.text = category.name
            view.setOnClickListener {
                onClickTopMenuListener.onClickTopMenu(category)
            }
        }

    }

    fun add(categoryList : ArrayList<Category>){
        categoryList.addAll(categoryList)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
     return categoryList.size
    }


}