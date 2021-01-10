package com.example.dindinapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dindinapp.R
import com.example.dindinapp.models.CategoryResponse

// Created by Gbenga Oladipupo(Devmike01) on 1/8/21.


class TopMenuAdapter: RecyclerView.Adapter<TopMenuAdapter.TopMenuViewHolder>() {

    interface OnClickTopMenuListener{
        fun onClickTopMenu(categoryResponse: CategoryResponse)
    }

    private lateinit var onClickTopMenuListener: OnClickTopMenuListener

    fun setOnClickTopMenuListener(onClickTopMenuListener: OnClickTopMenuListener){
        this.onClickTopMenuListener = onClickTopMenuListener
    }

    private val categoryList = ArrayList<CategoryResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMenuViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.top_menu_item_layout, parent, false)
        return TopMenuViewHolder(view, onClickTopMenuListener)
    }

    override fun onBindViewHolder(holder: TopMenuViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }


    class TopMenuViewHolder(private val view: View, private val onClickTopMenuListener: OnClickTopMenuListener?) : RecyclerView.ViewHolder(view){

        fun bind(categoryResponse: CategoryResponse){
            val menuTitleTv = view.findViewById<TextView>(R.id.menu_title_tv)
            menuTitleTv.text = categoryResponse.name
            view.setOnClickListener {
                if(onClickTopMenuListener ==null) {
                    onClickTopMenuListener?.onClickTopMenu(categoryResponse)
                }else{
                    Log.e("TopMenuAdapter", "OnClickTopMenuListener is null")
                }
            }
        }

    }

    fun add(categoryResponse : CategoryResponse){
        categoryList.add(categoryResponse)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
     return categoryList.size
    }


}