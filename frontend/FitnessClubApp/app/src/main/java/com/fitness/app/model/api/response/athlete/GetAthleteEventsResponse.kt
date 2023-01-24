package com.fitness.app.model.api.response.athlete

import com.fitness.app.model.api.Club
import com.google.gson.annotations.SerializedName

data class GetAthleteEventsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:ArrayList<GetAthleteEventsData>,
)

data class GetAthleteEventsData(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("club") val club: Club,
)