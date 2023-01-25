package com.fitness.app.api.endpoint

import com.fitness.app.model.api.request.diet.DiscoverDietsRequest
import com.fitness.app.model.api.request.diet.PickDietRequest
import com.fitness.app.model.api.response.diet.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DietEndpoints {
    @POST("diet/discover")
    fun discoverDiets(@Body discoverDietsRequest: DiscoverDietsRequest) : Call<DiscoverDietsResponse>

    @GET("diet/{diet_id}")
    fun getDietById(@Path(value = "diet_id", encoded = true) dietId:String) : Call<GetDietByIdResponse>

    @GET("diet/{diet_id}/foods")
    fun getDietFoods(@Path(value = "diet_id", encoded = true) dietId:String) : Call<GetDietsFoodsResponse>

    @GET("diet/{diet_id}/recipes")
    fun getDietRecipes(@Path(value = "diet_id", encoded = true) dietId:String) : Call<GetDietsRecipesResponse>

    @GET("diet/{diet_id}/comments")
    fun getDietComments(@Path(value = "diet_id", encoded = true) dietId:String) : Call<GetDietsCommentsResponse>

    @GET("diet/{diet_id}/cover")
    fun getDietCoverPicture(@Path(value = "diet_id", encoded = true) dietId:String) : Call<ResponseBody>

    @POST("diet/{diet_id}/pick")
    fun pickDiet(@Body pickDietRequest: PickDietRequest) : Call<PickDietResponse>

}