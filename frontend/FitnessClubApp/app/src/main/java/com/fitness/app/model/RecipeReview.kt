package com.fitness.app.model

import android.graphics.Bitmap
import com.fitness.app.model.api.response.recipe_review.Comment
import com.google.gson.annotations.SerializedName

data class RecipeReview(
    val image:Bitmap,
    @SerializedName("id") val id:Int,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("recipeId") val recipeId:Int,
    @SerializedName("comment") val comment: Comment,
)
