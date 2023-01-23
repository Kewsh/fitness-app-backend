package com.fitness.app.model.api.response.program

import com.fitness.app.model.Workout
import com.google.gson.annotations.SerializedName

data class GetProgramWorkoutsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:List<Workout>,
)
