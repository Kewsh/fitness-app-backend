package com.fitness.app.model.api.response.event

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
)
