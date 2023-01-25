package com.fitness.app.model.api.response.diet

import com.google.gson.annotations.SerializedName

data class PickDietResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: String,
)