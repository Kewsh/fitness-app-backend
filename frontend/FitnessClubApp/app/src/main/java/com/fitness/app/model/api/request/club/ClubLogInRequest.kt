package com.fitness.app.model.api.request.club

import com.google.gson.annotations.SerializedName

data class ClubLogInRequest(
    @SerializedName("email") val email:String,
    @SerializedName("password") val password:String
)