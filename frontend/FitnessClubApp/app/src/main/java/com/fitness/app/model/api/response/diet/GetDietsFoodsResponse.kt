package com.fitness.app.model.api.response.diet

import com.fitness.app.model.Food
import com.google.gson.annotations.SerializedName

data class GetDietsFoodsResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data: ArrayList<Food>,
)

data class GetDietsFoodsData(
    @SerializedName("amountAndTitle") val amountAndTitle:String,
    @SerializedName("id") val id:Int,
    @SerializedName("amount") val amount:String,
    @SerializedName("title") val title:String,
    @SerializedName("day") val day:Int,
)
