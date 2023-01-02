package com.fitness.app.model

data class DietPlan(
    val title:String,
    val isChecked: Int,
    val diets:List<Diet>,
)
