package com.fitness.app.model.api.response.club

import com.google.gson.annotations.SerializedName

data class GetClubByIdResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:GetClubByIdData,
)

data class GetClubByIdData(
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("manager") val manager:String,
    @SerializedName("description") val description:String,
    @SerializedName("since") val since:String?,
    @SerializedName("email") val email:String,
    @SerializedName("phoneNumber") val phoneNumber:String,
    @SerializedName("website") val website:String,
    @SerializedName("address") val address:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("socialMedia") val socialMedia:ArrayList<SocialMedia>,
    @SerializedName("nAthletes") val nAthletes:Int,
    @SerializedName("rating") val rating:Rating,

)

data class SocialMedia(
    @SerializedName("id") val id:Int,
    @SerializedName("type") val type:String,
    @SerializedName("url") val url:String,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("clubId") val clubId:Int,
)
