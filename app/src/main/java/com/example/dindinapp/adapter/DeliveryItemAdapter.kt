package com.example.dindinapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dindinapp.R
import com.example.dindinapp.models.FoodMenu

// Created by Gbenga Oladipupo(Devmike01) on 1/10/21.


class DeliveryItemAdapter : RecyclerView.Adapter<DeliveryItemAdapter.DeliveryItemViewHolder>() {

    private var foodMenuList: List<FoodMenu>? =null

    private var mCurrency: String? =null
    private var sum: Int =0
    private var onSumCalculate: OnSumCalculatorListener? = null

    public fun setOnSumCalculatorListener(onSumCalculate: OnSumCalculatorListener){
        this.onSumCalculate = onSumCalculate
    }

    interface OnSumCalculatorListener{
        fun onSumCalculate(total: String)
    }

    fun submitList(foodMenuList: List<FoodMenu>){
        this.foodMenuList = foodMenuList
        notifyDataSetChanged()
    }

    class DeliveryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindTo(foodMenu: FoodMenu){
            val removeItemTv = itemView.findViewById<TextView>(R.id.remove_tv)
            val nameTv = itemView.findViewById<TextView>(R.id.name_tv)
            val foodImageView = itemView.findViewById<ImageView>(R.id.food_iv)
            Glide.with(itemView.context).load(foodMenu.image).into(foodImageView)
            nameTv.text = foodMenu.name
            "${foodMenu.price} ${foodMenu.currency}".also { removeItemTv.text = it }
        }
    }

    override fun getItemCount(): Int = if(foodMenuList.isNullOrEmpty() ){0}else{foodMenuList!!.size}

    override fun onBindViewHolder(holder: DeliveryItemViewHolder, position: Int) {
        foodMenuList?.get(position)?.apply {
           sum += this.price
            mCurrency = currency
            onSumCalculate?.onSumCalculate("$sum $currency")
            Log.d("DeliveryItemAdapter", "Sum $sum")
            holder.bindTo(this)
        }
    }

    fun getSum(): String   = "$mCurrency $sum"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_list_item, parent, false)
        return DeliveryItemViewHolder(view)
    }
}