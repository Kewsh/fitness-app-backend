package com.fitness.app.model.api.response.recipe

import com.fitness.app.model.RecipeReview
import com.fitness.app.model.api.response.recipe_review.Comment
import com.google.gson.annotations.SerializedName

data class GetRecipeReviewsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:ArrayList<RecipeReview>,
)

