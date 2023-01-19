package com.fitness.app.model

import com.google.gson.annotations.SerializedName

data class AthleteSignUpResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("firstName") val firstName:String,
    @SerializedName("lastName") val lastName:String,
    @SerializedName("email") val email:String,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("profilePicPath") val profilePicPath: String?,
    @SerializedName("programEnrolmentDate") val programEnrolmentDate: String?,
    @SerializedName("dietPickDate") val dietPickDate: String?,
    @SerializedName("currentWeight") val currentWeight: String?,
    @SerializedName("startWeight") val startWeight: String?,
    @SerializedName("targetWeight") val targetWeight: String?,
    @SerializedName("currentWaistWidth") val currentWaistWidth: String?,
    @SerializedName("startWaistWidth") val startWaistWidth: String?,
    @SerializedName("targetWaistWidth") val targetWaistWidth: String?,
    @SerializedName("currentBicepWidth") val currentBicepWidth: String?,
    @SerializedName("startBicepWidth") val startBicepWidth: String?,
    @SerializedName("targetBicepWidth") val targetBicepWidth: String?,
    @SerializedName("programId") val programId: String?,
    @SerializedName("dietId") val dietId: String?,
)
