package com.fitness.app.model.api.response.club

import com.fitness.app.model.Event
import com.google.gson.annotations.SerializedName

data class GetClubEventsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:ArrayList<Event>,
)
