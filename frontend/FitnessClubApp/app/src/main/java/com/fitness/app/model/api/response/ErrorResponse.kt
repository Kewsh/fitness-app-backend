package com.fitness.app.model.api.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message:String,
)
