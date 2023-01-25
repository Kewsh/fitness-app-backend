package com.fitness.app.model.api.response.recipe

import com.fitness.app.model.Recipe
import com.fitness.app.model.api.response.club.Rating
import com.google.gson.annotations.SerializedName

data class GetRecipeByIdResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: Recipe,
)

data class RecipeIngredients(
    @SerializedName("amountAndTitle") val amountAndTitle:String,
    @SerializedName("title") val title:String,
    @SerializedName("amount") val amount:String,
)

data class Diet(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
)