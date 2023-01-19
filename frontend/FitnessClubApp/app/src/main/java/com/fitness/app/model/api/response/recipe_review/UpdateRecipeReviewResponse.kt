package com.fitness.app.model.api.response.recipe_review

import com.google.gson.annotations.SerializedName

data class UpdateRecipeReviewResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: String,
)
