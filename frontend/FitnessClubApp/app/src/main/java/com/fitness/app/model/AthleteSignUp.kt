package com.fitness.app.model

import com.google.gson.annotations.SerializedName

data class AthleteSignUp(
    @SerializedName("firstName") val firstName:String,
    @SerializedName("lastName") val lastName:String,
    @SerializedName("email") val email:String,
    @SerializedName("password") val password:String
)
