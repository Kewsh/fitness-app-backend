package com.fitness.app.model.api.request.athlete

import com.google.gson.annotations.SerializedName

data class AthleteLogInRequest(
    @SerializedName("email") val email:String,
    @SerializedName("password") val password:String
)
