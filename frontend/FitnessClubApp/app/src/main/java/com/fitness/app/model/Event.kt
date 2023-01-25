package com.fitness.app.model

import android.graphics.Bitmap
import com.fitness.app.model.api.Club
import com.fitness.app.model.api.response.club.Rating
import com.google.gson.annotations.SerializedName

data class Event(
    val image:Bitmap,
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String?,
    @SerializedName("description") val description:String?,
    @SerializedName("price") val price:Int,
    @SerializedName("maxAttendees") val maxAttendees:Int?,
    @SerializedName("startDate") val startDate:String?,
    @SerializedName("endDate") val endDate:String?,
    @SerializedName("createdAt") val createdAt:String?,
    @SerializedName("updatedAt") val updatedAt:String?,
    @SerializedName("clubId") val clubId:Int?,
    @SerializedName("club") val club: Club,
    @SerializedName("nAttendees") val nAttendees:Int?,
    @SerializedName("rating") val rating: Rating?,
)
