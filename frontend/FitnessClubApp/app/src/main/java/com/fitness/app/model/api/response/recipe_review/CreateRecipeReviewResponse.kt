package com.fitness.app.model.api.response.recipe_review

import com.fitness.app.model.api.response.comment.User
import com.google.gson.annotations.SerializedName

data class CreateRecipeReviewResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: CreateRecipeReviewData,
)

data class CreateRecipeReviewData(
    @SerializedName("id") val status:Int,
    @SerializedName("recipeId") val code:Int,
    @SerializedName("comment") val comment:Comment,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("reviewPicPath") val reviewPicPath:String?,
)

data class Comment(
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
