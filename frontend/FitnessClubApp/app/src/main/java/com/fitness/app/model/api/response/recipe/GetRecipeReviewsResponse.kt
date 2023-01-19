package com.fitness.app.model.api.response.recipe

import com.fitness.app.model.api.response.recipe_review.Comment
import com.google.gson.annotations.SerializedName

data class GetRecipeReviewsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:ArrayList<GetRecipeReviewsData>,
)

data class GetRecipeReviewsData(
    @SerializedName("id") val id:Int,
    @SerializedName("createdAt") val createdAt:Int,
    @SerializedName("updatedAt") val updatedAt:Int,
    @SerializedName("recipeId") val recipeId:Int,
    @SerializedName("comment") val comment:Comment,
)
