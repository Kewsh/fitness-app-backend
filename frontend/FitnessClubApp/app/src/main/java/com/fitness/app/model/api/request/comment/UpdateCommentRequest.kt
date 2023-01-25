package com.fitness.app.model.api.request.comment

import com.google.gson.annotations.SerializedName

data class UpdateCommentRequest(
    @SerializedName("text") val text:String,
    @SerializedName("rate") val rate:Int,
)
