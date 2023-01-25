package com.fitness.app.model.api.response.diet

import com.fitness.app.model.Comment
import com.google.gson.annotations.SerializedName

data class GetDietsCommentsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: ArrayList<Comment>,
)
