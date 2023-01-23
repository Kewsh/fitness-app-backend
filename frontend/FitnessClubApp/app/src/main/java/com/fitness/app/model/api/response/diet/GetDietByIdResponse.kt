package com.fitness.app.model.api.response.diet

import com.fitness.app.model.Diet
import com.fitness.app.model.api.response.club.Rating
import com.google.gson.annotations.SerializedName

data class GetDietByIdResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: Diet,
)