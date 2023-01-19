package com.fitness.app.model.api.response

import com.google.gson.annotations.SerializedName

data class ClubLogInResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:ClubLogInData,
)

data class ClubLogInData(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("manager") val manager:String,
    @SerializedName("description") val description:String,
    @SerializedName("since") val since:String?,
    @SerializedName("email") val email:String,
    @SerializedName("phoneNumber") val phoneNumber:String,
    @SerializedName("website") val website:String,
    @SerializedName("address") val address:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("nAthletes") val nAthletes:Int,
    @SerializedName("rating") val rating:Rating,
)

data class Rating(
    @SerializedName("rating") val rating:String?,
    @SerializedName("nRates") val nRates:Int,
)
