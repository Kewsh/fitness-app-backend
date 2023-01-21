package com.fitness.app.api.endpoint

import com.fitness.app.model.api.request.recipe_review.CreateRecipeReviewRequest
import com.fitness.app.model.api.request.recipe_review.UpdateRecipeReviewRequest
import com.fitness.app.model.api.response.recipe_review.CreateRecipeReviewResponse
import com.fitness.app.model.api.response.recipe_review.DeleteRecipeReviewPictureResponse
import com.fitness.app.model.api.response.recipe_review.DeleteRecipeReviewResponse
import com.fitness.app.model.api.response.recipe_review.UpdateRecipeReviewResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RecipeReviewEndpoints {
    @POST("recipe-review")
    fun createRecipeReview(@Body createRecipeReviewRequest: CreateRecipeReviewRequest) : Call<CreateRecipeReviewResponse>

    @PATCH("recipe-review/{recipe_review_id}")
    fun updateRecipeReview(@Path(value = "recipe_review_id", encoded = true) recipeReviewId:String , @Body updateRecipeReviewRequest: UpdateRecipeReviewRequest) : Call<UpdateRecipeReviewResponse>

    @DELETE("recipe-review/{recipe_review_id}")
    fun deleteRecipeReview(@Path(value = "recipe_review_id", encoded = true) recipeReviewId:String) : Call<DeleteRecipeReviewResponse>

    @GET("recipe-review/{recipe_review_id}/picture")
    fun getRecipeReviewPicture(@Path(value = "recipe_review_id", encoded = true) recipeReviewId:String) : Call<ResponseBody>

//    @PUT("recipe-review/{recipe_review_id}/picture")
//    fun setRecipeReviewPicture(@Path(value = "recipe_review_id", encoded = true) recipeReviewId:String , @Body setRecipeReviewPictureRequest: SetRecipeReviewPictureRequest) : Call<ResponseBody>

    @DELETE("recipe-review/{recipe_review_id}/picture")
    fun deleteRecipeReviewPicture(@Path(value = "recipe_review_id", encoded = true) recipeReviewId:String) : Call<DeleteRecipeReviewPictureResponse>

}