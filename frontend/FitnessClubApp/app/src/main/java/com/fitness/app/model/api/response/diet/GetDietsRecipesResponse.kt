package com.fitness.app.model.api.response.diet

import com.google.gson.annotations.SerializedName

data class GetDietsRecipesResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: ArrayList<GetDietsRecipesData>,
)

data class GetDietsRecipesData(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
)