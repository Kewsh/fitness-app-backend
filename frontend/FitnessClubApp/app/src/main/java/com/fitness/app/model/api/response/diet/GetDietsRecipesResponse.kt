package com.fitness.app.model.api.response.diet

import com.fitness.app.model.Recipe
import com.google.gson.annotations.SerializedName

data class GetDietsRecipesResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: ArrayList<Recipe>,
)