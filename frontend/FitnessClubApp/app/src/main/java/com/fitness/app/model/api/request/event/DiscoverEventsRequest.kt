package com.fitness.app.model.api.request.event

import com.google.gson.annotations.SerializedName

data class DiscoverEventsRequest(
    @SerializedName("userId") val userId:Int,
)
