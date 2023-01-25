package com.fitness.app.util

import com.fitness.app.model.api.response.recipe.RecipeIngredients

fun constructRecipeIngredients(recipeIngredients: ArrayList<RecipeIngredients>?) : String{
    var res:String = ""
    if (recipeIngredients != null) {
        for (recipe in recipeIngredients){
            res = res+"\u2022 "+recipe.amountAndTitle+"\n"
        }
    }
    return res
}