package com.fitness.app.model.api.response.diet

import com.google.gson.annotations.SerializedName

data class GetDietsCommentsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: ArrayList<GetDietsCommentsData>,
)

data class GetDietsCommentsData(
    @SerializedName("id") val id:Int,
    @SerializedName("text") val text:String,
    @SerializedName("rate") val rate:Int,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("programId") val programId:Int,
    @SerializedName("eventId") val eventId:Int,
    @SerializedName("dietId") val dietId:Int,
    @SerializedName("recipeReviewId") val recipeReviewId:Int,
    @SerializedName("userId") val userId:String?,
    @SerializedName("user") val user:String?,
)
