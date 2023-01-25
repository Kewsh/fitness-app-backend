package com.fitness.app.model.api.response.comment

import com.fitness.app.model.Comment
import com.google.gson.annotations.SerializedName

data class CreateCommentResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: Comment,
)

data class User(
    @SerializedName("id") val id:Int,
    @SerializedName("fullName") val fullName:String,
    @SerializedName("firstName") val firstName:String,
    @SerializedName("lastName") val lastName:String,
)