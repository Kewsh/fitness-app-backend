package com.fitness.app.api.endpoint

import com.fitness.app.model.api.response.event.GetEventByIdResponse
import com.fitness.app.model.api.response.nutritionist.GetNutritionistResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NutritionistEndpoints {
    @GET("nutritionist/{nutritionist_id}/diets")
    fun getNutNutritionistDiets(@Path(value = "nutritionist_id", encoded = true) nutritionistId:String) : Call<GetNutritionistResponse>

//    @GET("nutritionist/{nutritionist_id}/picture")
//    fun getNutNutritionistPicture(@Path(value = "nutritionist_id", encoded = true) nutritionistId:String) : Call<GetNutritionistPictureResponse>
}