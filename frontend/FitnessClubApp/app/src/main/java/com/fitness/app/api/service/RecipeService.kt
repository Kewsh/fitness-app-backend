package com.fitness.app.api.service

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.fitness.app.api.ServiceBuilder
import com.fitness.app.api.endpoint.RecipeEndpoints
import com.fitness.app.model.api.response.recipe.GetRecipeByIdResponse
import com.fitness.app.model.api.response.recipe.GetRecipeReviewsResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeService(val context: Context) {
    fun getRecipeCoverPicture(recipeId:String , onResult: (Bitmap?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RecipeEndpoints::class.java)
        retrofit.getRecipeCoverPicture(recipeId).enqueue(
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
                    else Log.e("RecipeCoverPic Result","failed")

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("RecipeCoverPic fail","RecipeCoverPic fail")
                    onResult(null)
                }

            }
        )
    }

    fun getRecipeReviews(recipeId: String, onResult: (GetRecipeReviewsResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RecipeEndpoints::class.java)
        retrofit.getRecipeReviews(recipeId).enqueue(
            object : Callback<GetRecipeReviewsResponse> {
                override fun onFailure(call: Call<GetRecipeReviewsResponse>, t: Throwable) {
                    Log.e("t",t.message.toString())
                    onResult(null)
                }
                override fun onResponse(call: Call<GetRecipeReviewsResponse>, response: Response<GetRecipeReviewsResponse>) {
                    onResult(response.body())
                }
            }
        )
    }

    fun getRecipeById(recipeId: String, onResult: (GetRecipeByIdResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RecipeEndpoints::class.java)
        retrofit.getRecipeById(recipeId).enqueue(
            object : Callback<GetRecipeByIdResponse> {
                override fun onFailure(call: Call<GetRecipeByIdResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<GetRecipeByIdResponse>, response: Response<GetRecipeByIdResponse>) {
                    onResult(response.body())
                }
            }
        )
    }
}