package com.fitness.app.api.endpoint

import com.fitness.app.model.api.response.recipe.GetRecipeByIdResponse
import com.fitness.app.model.api.response.recipe.GetRecipeReviewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeEndpoints {
    @GET("recipe/{recipe_id}")
    fun getRecipeById(@Path(value = "recipe_id", encoded = true) recipeId:String) : Call<GetRecipeByIdResponse>

    @GET("recipe/{recipe_id}/reviews")
    fun getRecipeReviews(@Path(value = "recipe_id", encoded = true) recipeId:String) : Call<GetRecipeReviewsResponse>

//    @GET("recipe/{recipe_id}/cover")
//    fun getRecipeCoverPicture(@Path(value = "recipe_id", encoded = true) recipeId:String) : Call<GetRecipeCoverPictureResponse>
}