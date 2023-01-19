package com.fitness.app.model.api.response.workout

import com.google.gson.annotations.SerializedName

data class GetWorkoutByIdResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:GetWorkoutByIdData,
)

data class GetWorkoutByIdData(
    @SerializedName("id") val id:Int,
    @SerializedName("setsAndReps") val setsAndReps:String,
    @SerializedName("title") val title:String,
    @SerializedName("description") val description:String?,
    @SerializedName("sets") val sets:Int,
    @SerializedName("reps") val reps:Int?,
    @SerializedName("setTimeInSeconds") val setTimeInSeconds:Int?,
    @SerializedName("burntCalories") val burntCalories:Int?,
    @SerializedName("day") val day:Int,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("programId") val programId:Int,
    @SerializedName("program") val program:Program,
)

data class Program(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
)
