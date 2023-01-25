package com.fitness.app.model.api.response.event

import com.fitness.app.model.Event
import com.fitness.app.model.api.Club
import com.fitness.app.model.api.response.club.Rating
import com.google.gson.annotations.SerializedName

data class GetEventByIdResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: Event,
)
