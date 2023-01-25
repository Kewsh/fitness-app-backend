package com.fitness.app.api.endpoint

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodEndpoints {
    @GET("food/{food_id}/cover")
    fun getFoodCoverPicture(@Path(value = "food_id", encoded = true) foodId:String) : Call<ResponseBody>
}