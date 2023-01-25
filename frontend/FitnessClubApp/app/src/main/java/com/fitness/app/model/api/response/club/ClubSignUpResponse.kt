package com.fitness.app.model.api.response.club

import com.google.gson.annotations.SerializedName

data class ClubSignUpResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:ClubSignUpData,
)

data class ClubSignUpData(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("manager") val manager:String,
    @SerializedName("description") val description:String,
    @SerializedName("email") val email:String,
    @SerializedName("phoneNumber") val phoneNumber:String,
    @SerializedName("website") val website:String,
    @SerializedName("address") val address:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("since") val since:String?,
)