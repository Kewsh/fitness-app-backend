package com.fitness.app.model.api.response.workout

import com.fitness.app.model.Workout
import com.google.gson.annotations.SerializedName

data class GetWorkoutByIdResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:Workout,
)
