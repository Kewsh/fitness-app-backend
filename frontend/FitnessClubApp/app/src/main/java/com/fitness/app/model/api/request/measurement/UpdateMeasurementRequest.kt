package com.fitness.app.model.api.request.measurement

import com.google.gson.annotations.SerializedName

data class UpdateMeasurementRequest(
    @SerializedName("start") val start:Int,
    @SerializedName("current") val current:Int,
    @SerializedName("target") val target:Int,
)