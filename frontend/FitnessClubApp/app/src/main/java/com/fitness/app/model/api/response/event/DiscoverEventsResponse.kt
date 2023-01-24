package com.fitness.app.model.api.response.event

import com.fitness.app.model.api.Club
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
    @SerializedName("club") val club: Club,
)
