package com.fitness.app.model

import android.graphics.Bitmap
import com.fitness.app.model.api.response.club.Rating
import com.fitness.app.model.api.response.diet.Nutritionist
import com.google.gson.annotations.SerializedName

data class Diet(
    val image:Bitmap,
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("description") val description:String?,
    @SerializedName("price") val price:Int,
    @SerializedName("createdAt") val createdAt:String?,
    @SerializedName("updatedAt") val updatedAt:String?,
    @SerializedName("nutritionistId") val nutritionistId:Int,
    @SerializedName("duration") val duration:Int?,
    @SerializedName("nAthletes") val nAthletes:Int,
    @SerializedName("rating") val rating: Rating?,
    @SerializedName("nutritionist") val nutritionist: Nutritionist,
)
