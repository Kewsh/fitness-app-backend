package com.fitness.app.model.api.request

import com.google.gson.annotations.SerializedName

data class ClubSignUpRequest(
    @SerializedName("name") val name:String,
    @SerializedName("manager") val manager:String,
    @SerializedName("description") val description:String,
    @SerializedName("email") val email:String,
    @SerializedName("password") val password:String,
    @SerializedName("phoneNumber") val phoneNumber:String,
    @SerializedName("website") val website:String,
    @SerializedName("address") val address:String
)
