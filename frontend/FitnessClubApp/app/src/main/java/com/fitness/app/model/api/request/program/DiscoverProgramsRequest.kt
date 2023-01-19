package com.fitness.app.model.api.request.program

import com.google.gson.annotations.SerializedName

data class DiscoverProgramsRequest(
    @SerializedName("userId") val userId:Int,
)
