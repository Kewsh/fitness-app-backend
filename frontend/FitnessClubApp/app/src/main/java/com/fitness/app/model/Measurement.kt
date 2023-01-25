package com.fitness.app.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Measurement: Serializable {
    @SerializedName("id") val id: Int = 0
    @SerializedName("type") val type:String = ""
    @SerializedName("userId") val userId:String = ""
    @SerializedName("updatedAt") val updatedAt:String = ""
    @SerializedName("createdAt") val createdAt:String = ""
    @SerializedName("current") val current:String? = null
    @SerializedName("start") val start:String? = null
    @SerializedName("target") val target:String? = null
    @SerializedName("progressPercentage") val progressPercentage:Int? = null
}