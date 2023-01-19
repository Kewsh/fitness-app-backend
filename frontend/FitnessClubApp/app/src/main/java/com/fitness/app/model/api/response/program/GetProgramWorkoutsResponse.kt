package com.fitness.app.model.api.response.program

import com.google.gson.annotations.SerializedName

data class GetProgramWorkoutsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:ArrayList<GetProgramWorkoutsData>,
)

data class GetProgramWorkoutsData(
    @SerializedName("id") val id:Int,
    @SerializedName("setsAndReps") val setsAndReps:String,
    @SerializedName("title") val title:String,
    @SerializedName("sets") val sets:Int,
    @SerializedName("reps") val reps:Int?,
    @SerializedName("setTimeInSeconds") val setTimeInSeconds:Int?,
    @SerializedName("day") val day:Int,
)
