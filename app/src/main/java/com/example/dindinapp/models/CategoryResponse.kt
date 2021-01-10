package com.example.dindinapp.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryResponse(
    @SerializedName("filter")
    @Expose
    val foodFilterResponse: ArrayList<FoodFilterResponse>,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    val isClicked: Boolean =false
): Parcelable