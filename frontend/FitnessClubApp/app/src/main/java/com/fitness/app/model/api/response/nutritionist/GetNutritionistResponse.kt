package com.fitness.app.model.api.response.nutritionist

import com.fitness.app.model.api.response.diet.Nutritionist
import com.google.gson.annotations.SerializedName

data class GetNutritionistResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: ArrayList<GetNutritionistData>,
)

data class GetNutritionistData(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("nutritionist") val nutritionist:Nutritionist,
)