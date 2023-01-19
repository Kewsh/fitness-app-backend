package com.fitness.app.model

import com.google.gson.annotations.SerializedName

data class AthleteLogIn(
    @SerializedName("email") val email:String,
    @SerializedName("password") val password:String
)
