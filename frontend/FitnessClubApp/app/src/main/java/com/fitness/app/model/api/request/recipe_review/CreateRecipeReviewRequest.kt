package com.fitness.app.model.api.request.recipe_review

import com.google.gson.annotations.SerializedName

data class CreateRecipeReviewRequest(
    @SerializedName("text") val text:String,
    @SerializedName("rate") val rate:Int,
    @SerializedName("recipeId") val recipeId:Int,
    @SerializedName("userId") val userId:Int,
)