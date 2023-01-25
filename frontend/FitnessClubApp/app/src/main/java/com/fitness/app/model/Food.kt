package com.fitness.app.model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class Food(
    var image: Bitmap,
    @SerializedName("amountAndTitle") val amountAndTitle:String,
    @SerializedName("id") val id:Int,
    @SerializedName("amount") val amount:String,
    @SerializedName("title") val title:String,
    @SerializedName("day") val day:Int,
)
