package com.fitness.app.model.api.request.diet

import com.google.gson.annotations.SerializedName

data class DiscoverDietsRequest(
    @SerializedName("userId") val userId:Int,
)
