package com.fitness.app.model.api.request.diet

import com.google.gson.annotations.SerializedName

data class PickDietRequest(
    @SerializedName("userId") val userId:Int,
)
