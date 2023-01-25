package com.fitness.app.model.api.response.nutritionist

import com.fitness.app.model.Diet
import com.fitness.app.model.api.response.diet.Nutritionist
import com.google.gson.annotations.SerializedName

data class GetNutritionistDietsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: ArrayList<Diet>,
)