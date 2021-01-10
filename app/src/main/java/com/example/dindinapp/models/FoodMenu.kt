package com.example.dindinapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodMenu(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("recipe")
    @Expose
    val recipe: List<String>,
    @SerializedName("size")
    @Expose
    val size: String,
    @SerializedName("image")
    @Expose
    val image: String,
    @SerializedName("menu")
    @Expose
    var counter: Int =0,
    @SerializedName("isPromo")
    @Expose
    val isPromo: Boolean = false,
    @SerializedName("price")
    @Expose
    var price: Int =0,
    @SerializedName("currency")
    @Expose
    var currency: String? =null

)