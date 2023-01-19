package com.fitness.app.model.api.response.diet

import com.fitness.app.model.api.response.club.Rating
import com.google.gson.annotations.SerializedName

data class DiscoverDietsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: ArrayList<DiscoverDietsData>,
)

data class DiscoverDietsData(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("nutritionist") val nutritionist:Nutritionist,
)

data class Nutritionist(
    @SerializedName("id") val id:Int,
    @SerializedName("fullName") val fullName:String,
    @SerializedName("description") val description:String?,
    @SerializedName("since") val since:String?,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("nAthletes") val nAthletes:Int,
    @SerializedName("rating") val rating: Rating,
)