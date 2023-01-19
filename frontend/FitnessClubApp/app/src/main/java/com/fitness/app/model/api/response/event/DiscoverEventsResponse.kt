package com.fitness.app.model.api.response.event

import com.fitness.app.model.api.response.club.Rating
import com.google.gson.annotations.SerializedName

data class DiscoverEventsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:ArrayList<DiscoverEventsData>,
)

data class DiscoverEventsData(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("club") val club:Club,
)

data class Club(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("manager") val manager:String,
    @SerializedName("description") val description:String,
    @SerializedName("since") val since:String?,
    @SerializedName("email") val email:String,
    @SerializedName("phoneNumber") val phoneNumber:String,
    @SerializedName("website") val website:String,
    @SerializedName("address") val address:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("nAthletes") val nAthletes:Int,
    @SerializedName("rating") val rating: Rating,
)
