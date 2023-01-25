package com.fitness.app.model

import android.graphics.Bitmap
import com.fitness.app.model.api.response.club.Rating
import com.fitness.app.model.api.response.recipe.Diet
import com.fitness.app.model.api.response.recipe.RecipeIngredients
import com.google.gson.annotations.SerializedName

data class Recipe(
    val image: Bitmap,
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("description") val description:String?,
    @SerializedName("origin") val origin:String?,
    @SerializedName("prepTimeInMinutes") val prepTimeInMinutes:Int,
    @SerializedName("servings") val servings:Int,
    @SerializedName("price") val price:Int,
    @SerializedName("stepByStepGuide") val stepByStepGuide:String?,
    @SerializedName("createdAt") val createdAt:String?,
    @SerializedName("updatedAt") val updatedAt:String?,
    @SerializedName("dietId") val dietId:Int,
    @SerializedName("recipeIngredients") val recipeIngredients:ArrayList<RecipeIngredients>?,
    @SerializedName("diet") val diet: Diet?,
    @SerializedName("rating") val rating: Rating?,
)
