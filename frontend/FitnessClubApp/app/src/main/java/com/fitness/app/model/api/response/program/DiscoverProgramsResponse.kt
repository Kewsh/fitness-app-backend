package com.fitness.app.model.api.response.program

import com.fitness.app.model.api.response.event.Club
import com.google.gson.annotations.SerializedName

data class DiscoverProgramsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:ArrayList<DiscoverProgramsData>,
)

data class DiscoverProgramsData(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("club") val club: Club,
)