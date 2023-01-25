package com.fitness.app.model.api.response.event

import com.google.gson.annotations.SerializedName

data class ParticipateInEventResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: ArrayList<ParticipateInEventData>,
)

data class ParticipateInEventData(
    @SerializedName("eventId") val eventId:Int,
    @SerializedName("userId") val userId:Int,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("updatedAt") val updatedAt:String,
)
