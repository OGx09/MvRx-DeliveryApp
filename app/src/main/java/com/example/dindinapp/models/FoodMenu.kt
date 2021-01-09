package com.example.dindinapp.models

data class FoodMenu(
    val name: String,
    val recipe: List<String>,
    val size: String,
    val image: String,
    var counter: Int =0,
    val isPromo: Boolean = false
)