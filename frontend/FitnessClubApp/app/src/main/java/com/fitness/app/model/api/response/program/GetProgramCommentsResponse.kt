package com.fitness.app.model.api.response.program

import com.fitness.app.model.Comment
import com.google.gson.annotations.SerializedName

data class GetProgramCommentsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: ArrayList<Comment>,
)
