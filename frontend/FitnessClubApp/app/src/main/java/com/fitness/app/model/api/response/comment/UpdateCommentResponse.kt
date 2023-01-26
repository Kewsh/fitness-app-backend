package com.fitness.app.model.api.response.comment

import com.google.gson.annotations.SerializedName

data class UpdateCommentResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:String,
)