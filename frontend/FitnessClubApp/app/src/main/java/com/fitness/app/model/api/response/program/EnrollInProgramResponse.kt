package com.fitness.app.model.api.response.program

import com.google.gson.annotations.SerializedName

data class EnrollInProgramResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: String,
)
