package com.fitness.app.model.api.response.diet

import com.fitness.app.model.api.response.club.Rating
import com.google.gson.annotations.SerializedName

data class GetDietByIdResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: GetDietByIdData,
)

data class GetDietByIdData(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("description") val description:String,
    @SerializedName("price") val price:Int,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("nutritionistId") val nutritionistId:Int,
    @SerializedName("duration") val duration:Int?,
    @SerializedName("nAthletes") val nAthletes:Int,
    @SerializedName("rating") val rating:Rating,
    @SerializedName("nutritionist") val nutritionist:Nutritionist,
)