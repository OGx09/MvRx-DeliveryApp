package com.example.dindinapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodFilterResponse(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("menu")
    @Expose
    val foodMenus: List<FoodMenu>,
    @SerializedName("name")
    @Expose
    val name: String
)