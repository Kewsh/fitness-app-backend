package com.fitness.app.api.endpoint

import com.fitness.app.model.api.request.program.DiscoverProgramsRequest
import com.fitness.app.model.api.request.recipe_review.CreateRecipeReviewRequest
import com.fitness.app.model.api.request.recipe_review.UpdateRecipeReviewRequest
import com.fitness.app.model.api.response.program.DiscoverProgramsResponse
import com.fitness.app.model.api.response.program.GetProgramByIdResponse
import com.fitness.app.model.api.response.recipe_review.CreateRecipeReviewResponse
import com.fitness.app.model.api.response.recipe_review.DeleteRecipeReviewResponse
import com.fitness.app.model.api.response.recipe_review.UpdateRecipeReviewResponse
import retrofit2.Call
import retrofit2.http.*

interface RecipeReviewEndpoints {
    @POST("recipe-review")
    fun createRecipeReview(@Body createRecipeReviewRequest: CreateRecipeReviewRequest) : Call<CreateRecipeReviewResponse>

    @PATCH("recipe-review/{recipe_review_id}")
    fun updateRecipeReview(@Path(value = "recipe_review_id", encoded = true) recipeReviewId:String , @Body updateRecipeReviewRequest: UpdateRecipeReviewRequest) : Call<UpdateRecipeReviewResponse>

    @DELETE("recipe-review/{recipe_review_id}")
    fun deleteRecipeReview(@Path(value = "recipe_review_id", encoded = true) recipeReviewId:String) : Call<DeleteRecipeReviewResponse>

//    @GET("recipe-review/{recipe_review_id}/picture")
//    fun getRecipeReviewPicture(@Path(value = "recipe_review_id", encoded = true) recipeReviewId:String) : Call<GetRecipeReviewPictureResponse>

//    @PUT("recipe-review/{recipe_review_id}/picture")
//    fun setRecipeReviewPicture(@Path(value = "recipe_review_id", encoded = true) recipeReviewId:String , @Body setRecipeReviewPictureRequest: SetRecipeReviewPictureRequest) : Call<SetRecipeReviewPictureResponse>

//    @DELETE("recipe-review/{recipe_review_id}/picture")
//    fun deleteRecipeReviewPicture(@Path(value = "recipe_review_id", encoded = true) recipeReviewId:String) : Call<DeleteRecipeReviewPictureResponse>

}