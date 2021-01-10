package com.example.dindinapp.adapter

import android.os.Handler
import android.os.Looper
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
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
import com.example.dindinapp.models.FoodMenu

// Created by Gbenga Oladipupo(Devmike01) on 1/8/21.


class FoodAdapter : ListAdapter<FoodMenu, FoodAdapter.FoodViewViewHolder>(FoodCallback()) {


    interface OnClickFoodListener{
        fun onClickFood(foodMenu: FoodMenu)
    }

    private lateinit var  onClickFoodListener: OnClickFoodListener

    fun setOnAddFoodListener(onClickFoodListener: OnClickFoodListener){
        this.onClickFoodListener = onClickFoodListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return FoodViewViewHolder(view, onClickFoodListener)
    }

    override fun onBindViewHolder(holder: FoodViewViewHolder, position: Int) {

        Log.d("MainActivity@12", "Food list -> $position}")
        holder.bind(getItem(position))
    }


    class FoodViewViewHolder(private val view: View, private  val onClickFoodListener : OnClickFoodListener?) : RecyclerView.ViewHolder(view){

        fun bind(food: FoodMenu){
            val foodImageView = view.findViewById<ImageView>(R.id.food_image)
            val foodNameTv = view.findViewById<TextView>(R.id.food_title_tv)
            val foodDescTv = view.findViewById<TextView>(R.id.desc_tv)

            val sizePrizeView = view.findViewById<View>(R.id.size_prize_layout)
            val sizeTv = sizePrizeView.findViewById<TextView>(R.id.size_tv)
            val addFoodBtn = sizePrizeView.findViewById<Button>(R.id.add_btn)
            "${food.price} ${food.currency}".also { addFoodBtn.text = it }

            addFoodBtn.setOnClickListener {
                Handler(Looper.myLooper()!!).postDelayed({
                    "${food.price} ${food.currency}".also { addFoodBtn.text = it }
                }, 200)
                food.counter +=1
                addFoodBtn.text = "added +1"
                onClickFoodListener?.onClickFood(food)
            }


            sizeTv.text = food.size
            foodNameTv.text = food.name
            val recipeBuilder = StringBuffer()
             food.recipe.forEach {
                 recipeBuilder.append(it.plus(", "))
            }
            foodDescTv.text = recipeBuilder.toString()
            Glide.with(view.context).load(food.image).into(foodImageView)
        }

    }

    class FoodCallback : DiffUtil.ItemCallback<FoodMenu>() {
        override fun areContentsTheSame(oldItem: FoodMenu, newItem: FoodMenu): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: FoodMenu, newItem: FoodMenu): Boolean {
            return oldItem.name == newItem.name
        }
    }
}