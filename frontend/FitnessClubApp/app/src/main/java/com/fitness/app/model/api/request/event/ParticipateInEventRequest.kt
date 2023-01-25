package com.fitness.app.model.api.request.event

import com.google.gson.annotations.SerializedName

data class ParticipateInEventRequest(
    @SerializedName("userId") val userId:Int,
)
