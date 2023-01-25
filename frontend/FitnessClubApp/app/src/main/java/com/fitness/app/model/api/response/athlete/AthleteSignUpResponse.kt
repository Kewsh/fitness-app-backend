package com.fitness.app.model.api.response.athlete

import com.fitness.app.model.Measurement
import com.google.gson.annotations.SerializedName

data class AthleteSignUpResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:AthleteSignUpData,
)

data class AthleteSignUpData(
    @SerializedName("id") val id: Int,
    @SerializedName("firstName") val firstName:String,
    @SerializedName("lastName") val lastName:String,
    @SerializedName("email") val email:String,
    @SerializedName("measurements") val measurements:ArrayList<Measurement>,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("programEnrolmentDate") val programEnrolmentDate:String?,
    @SerializedName("dietPickDate") val dietPickDate:String?,
    @SerializedName("programId") val programId:Int?,
    @SerializedName("dietId") val dietId:Int?,
)

//data class Measurement(
//    @SerializedName("id") val id: Int,
//    @SerializedName("type") val type:String,
//    @SerializedName("userId") val userId:String,
//    @SerializedName("updatedAt") val updatedAt:String,
//    @SerializedName("createdAt") val createdAt:String,
//    @SerializedName("current") val current:String?,
//    @SerializedName("start") val start:String?,
//    @SerializedName("target") val target:String?,
//)
