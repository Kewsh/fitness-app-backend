package com.fitness.app.model.api.response.recipe

import com.fitness.app.model.api.response.club.Rating
import com.google.gson.annotations.SerializedName

data class GetRecipeByIdResponse(
    @SerializedName("status") val status:String,
    @SerializedName("code") val code:Int,
    @SerializedName("data") val data:GetRecipeByIdData,
)

data class GetRecipeByIdData(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("description") val description:String,
    @SerializedName("origin") val origin:String,
    @SerializedName("prepTimeInMinutes") val prepTimeInMinutes:Int,
    @SerializedName("servings") val servings:Int,
    @SerializedName("price") val price:Int,
    @SerializedName("stepByStepGuide") val stepByStepGuide:String,
    @SerializedName("createdAt") val createdAt:String,
    @SerializedName("updatedAt") val updatedAt:String,
    @SerializedName("dietId") val dietId:Int,
    @SerializedName("recipeIngredients") val recipeIngredients:ArrayList<RecipeIngredients>,
    @SerializedName("diet") val diet:Diet,
    @SerializedName("rating") val rating:Rating,
)

data class RecipeIngredients(
    @SerializedName("amountAndTitle") val amountAndTitle:String,
    @SerializedName("title") val title:String,
    @SerializedName("amount") val amount:String,
)

data class Diet(
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
)
//    "id": 1,
//    "title": "receipe1",
//    "description": "receipe desc1",
//    "origin": "sdfds",
//    "prepTimeInMinutes": 11,
//    "servings": 5,
//    "price": 14,
//    "stepByStepGuide": "dfgdh",
//    "createdAt": "2023-01-19T07:54:58.166Z",
//    "updatedAt": "2023-01-19T07:54:58.166Z",
//    "dietId": 1,
//    "recipeIngredients": [],
