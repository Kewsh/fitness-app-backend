package com.fitness.app.model.api.request.comment

import com.google.gson.annotations.SerializedName

data class CreateCommentRequest(
    @SerializedName("text") val text:String,
    @SerializedName("rate") val rate:Int,
    @SerializedName("programId") val programId:Int,
    @SerializedName("userId") val userId:Int,
)