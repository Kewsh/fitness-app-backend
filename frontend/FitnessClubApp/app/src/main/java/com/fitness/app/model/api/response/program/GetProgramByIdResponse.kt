package com.fitness.app.model.api.response.program

import com.fitness.app.model.api.Club
import com.fitness.app.model.api.response.club.Rating
import com.google.gson.annotations.SerializedName

data class GetProgramByIdResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:GetProgramByIdData,
)

data class GetProgramByIdData(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("description") val description:String?,
    @SerializedName("coachName") val coachName:String?,
    @SerializedName("price") val price:Int,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("clubId") val clubId:Int,
    @SerializedName("duration") val duration:Int,
    @SerializedName("nAthletes") val nAthletes:Int,
    @SerializedName("rating") val rating:Rating,
    @SerializedName("club") val club: Club,
)