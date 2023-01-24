package com.fitness.app.model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class Workout(
    var image:Bitmap,
    @SerializedName("id") val id:Int,
    @SerializedName("setsAndReps") val setsAndReps:String?,
    @SerializedName("title") val title:String,
    @SerializedName("description") val description:String?,
    @SerializedName("sets") val sets:Int?,
    @SerializedName("reps") val reps:Int?,
    @SerializedName("setTimeInSeconds") val setTimeInSeconds:Int?,
    @SerializedName("burntCalories") val burntCalories:Int?,
    @SerializedName("day") val day:Int?,
    @SerializedName("createdAt") val createdAt:String?,
    @SerializedName("updatedAt") val updatedAt:String?,
    @SerializedName("programId") val programId:Int?,
    @SerializedName("program") val program:Program?,
)
