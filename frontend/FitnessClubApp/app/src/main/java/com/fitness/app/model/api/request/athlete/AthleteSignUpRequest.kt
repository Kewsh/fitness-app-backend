package com.fitness.app.model.api.request.athlete

import com.google.gson.annotations.SerializedName

data class AthleteSignUpRequest(
    @SerializedName("firstName") val firstName:String,
    @SerializedName("lastName") val lastName:String,
    @SerializedName("email") val email:String,
    @SerializedName("password") val password:String
)
