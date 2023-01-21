package com.fitness.app.api.service

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.fitness.app.api.ServiceBuilder
import com.fitness.app.api.endpoint.DietEndpoints
import com.fitness.app.api.endpoint.RecipeReviewEndpoints
import com.fitness.app.model.api.request.recipe_review.CreateRecipeReviewRequest
import com.fitness.app.model.api.request.recipe_review.UpdateRecipeReviewRequest
import com.fitness.app.model.api.response.diet.GetDietByIdResponse
import com.fitness.app.model.api.response.recipe_review.CreateRecipeReviewResponse
import com.fitness.app.model.api.response.recipe_review.DeleteRecipeReviewPictureResponse
import com.fitness.app.model.api.response.recipe_review.DeleteRecipeReviewResponse
import com.fitness.app.model.api.response.recipe_review.UpdateRecipeReviewResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeReviewService(val context: Context) {
    fun deleteRecipeReviewPicture(recipeReviewId: String, onResult: (DeleteRecipeReviewPictureResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RecipeReviewEndpoints::class.java)
        retrofit.deleteRecipeReviewPicture(recipeReviewId).enqueue(
            object : Callback<DeleteRecipeReviewPictureResponse> {
                override fun onFailure(call: Call<DeleteRecipeReviewPictureResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<DeleteRecipeReviewPictureResponse>, response: Response<DeleteRecipeReviewPictureResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun getRecipeReviewPicture(recipeReviewId:String , onResult: (Bitmap?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RecipeReviewEndpoints::class.java)
        retrofit.getRecipeReviewPicture(recipeReviewId).enqueue(
            object :Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val res = response.body()
                    if(res!=null) {
                        val bmp = BitmapFactory.decodeStream(res.byteStream())
                        onResult(bmp)
                    }
                    else Log.e("RecipeReviewPic Result","failed")

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("RecipeReviewPic fail","RecipeReviewPic fail")
                    onResult(null)
                }

            }
        )
    }

    fun deleteRecipeReview(recipeReviewId: String, onResult: (DeleteRecipeReviewResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RecipeReviewEndpoints::class.java)
        retrofit.deleteRecipeReview(recipeReviewId).enqueue(
            object : Callback<DeleteRecipeReviewResponse> {
                override fun onFailure(call: Call<DeleteRecipeReviewResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<DeleteRecipeReviewResponse>, response: Response<DeleteRecipeReviewResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun updateRecipeReview(recipeReviewId:String , updateRecipeReviewRequest: UpdateRecipeReviewRequest, onResult: (UpdateRecipeReviewResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RecipeReviewEndpoints::class.java)
        retrofit.updateRecipeReview(recipeReviewId,updateRecipeReviewRequest).enqueue(
            object : Callback<UpdateRecipeReviewResponse> {
                override fun onFailure(call: Call<UpdateRecipeReviewResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<UpdateRecipeReviewResponse>, response: Response<UpdateRecipeReviewResponse>) {
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }

    fun createRecipeReview(createRecipeReviewRequest: CreateRecipeReviewRequest, onResult: (CreateRecipeReviewResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RecipeReviewEndpoints::class.java)
        retrofit.createRecipeReview(createRecipeReviewRequest).enqueue(
            object : Callback<CreateRecipeReviewResponse> {
                override fun onFailure(call: Call<CreateRecipeReviewResponse>, t: Throwable) {
                    Log.e("onFailure",t.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<CreateRecipeReviewResponse>, response: Response<CreateRecipeReviewResponse>) {
                    val body = response.body()
                    Log.e("onResponse",body.toString())
                    onResult(body)
                }
            }
        )
    }

}