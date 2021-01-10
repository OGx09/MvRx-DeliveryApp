package com.example.dindinapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("filter")
    @Expose
    val foodFilterResponse: List<FoodFilterResponse>,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    val isClicked: Boolean =false
)