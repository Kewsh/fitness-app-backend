package com.fitness.app.model.api.response.athlete

import com.google.gson.annotations.SerializedName

data class AthleteLogInResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:AthleteLogInData,
)

data class AthleteLogInData(
    @SerializedName("id") val id: Int,
    @SerializedName("firstName") val firstName:String,
    @SerializedName("lastName") val lastName:String,
    @SerializedName("email") val email:String,
    @SerializedName("programEnrolmentDate") val programEnrolmentDate:String?,
    @SerializedName("dietPickDate") val dietPickDate:String?,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("programId") val programId:Int?,
    @SerializedName("dietId") val dietId:Int?,
    @SerializedName("measurements") val measurements:ArrayList<Measurement>,
)
