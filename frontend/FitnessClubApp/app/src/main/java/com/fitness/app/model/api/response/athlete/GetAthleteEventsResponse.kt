package com.fitness.app.model.api.response.athlete

import com.fitness.app.model.Event
import com.google.gson.annotations.SerializedName

data class GetAthleteEventsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:ArrayList<Event>,
)