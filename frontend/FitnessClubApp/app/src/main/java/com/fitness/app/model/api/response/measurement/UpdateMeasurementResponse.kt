package com.fitness.app.model.api.response.measurement

import com.google.gson.annotations.SerializedName

data class UpdateMeasurementResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: String,
)
