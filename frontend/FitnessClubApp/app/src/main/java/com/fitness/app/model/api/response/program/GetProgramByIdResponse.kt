package com.fitness.app.model.api.response.program

import com.fitness.app.model.Program
import com.google.gson.annotations.SerializedName

data class GetProgramByIdResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:Program,
)