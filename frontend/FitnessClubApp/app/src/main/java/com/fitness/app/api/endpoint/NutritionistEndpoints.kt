package com.fitness.app.api.endpoint

import com.fitness.app.model.api.response.nutritionist.GetNutritionistDietsResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NutritionistEndpoints {
    @GET("nutritionist/{nutritionist_id}/diets")
    fun getNutritionistDiets(@Path(value = "nutritionist_id", encoded = true) nutritionistId:String) : Call<GetNutritionistDietsResponse>

    @GET("nutritionist/{nutritionist_id}/picture")
    fun getNutritionistPicture(@Path(value = "nutritionist_id", encoded = true) nutritionistId:String) : Call<ResponseBody>
}