package com.fitness.app.model.api.response.program

import com.fitness.app.model.api.response.comment.CreateCommentData
import com.fitness.app.model.api.response.comment.User
import com.google.gson.annotations.SerializedName

data class GetProgramCommentsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: ArrayList<CreateCommentData>,
)

data class GetProgramCommentsData(
    @SerializedName("id") val id:Int,
    @SerializedName("text") val text:String,
    @SerializedName("rate") val rate:Int,
    @SerializedName("programId") val programId:Int,
    @SerializedName("eventId") val eventId:Int?,
    @SerializedName("dietId") val dietId:Int?,
    @SerializedName("recipeReviewId") val recipeReviewId:Int?,
    @SerializedName("userId") val userId:Int,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("user") val user: User,
)
