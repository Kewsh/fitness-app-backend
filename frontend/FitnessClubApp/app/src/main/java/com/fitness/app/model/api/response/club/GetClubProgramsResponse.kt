package com.fitness.app.model.api.response.club

import com.google.gson.annotations.SerializedName

data class GetClubProgramsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:ArrayList<GetClubProgramsData>,
)

data class GetClubProgramsData(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("club") val club:Club,
)

data class Club(
    @SerializedName("name") val name:String,
)