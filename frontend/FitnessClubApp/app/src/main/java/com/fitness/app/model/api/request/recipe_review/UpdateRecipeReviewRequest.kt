package com.fitness.app.model.api.request.recipe_review

import com.google.gson.annotations.SerializedName

data class UpdateRecipeReviewRequest(
    @SerializedName("rate") val rate:Int,
)
