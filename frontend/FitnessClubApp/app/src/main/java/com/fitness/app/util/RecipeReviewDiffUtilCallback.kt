package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.RecipeReview
import com.fitness.app.model.WhoTriedFood

class RecipeReviewDiffUtilCallback : DiffUtil.ItemCallback<RecipeReview>() {
    override fun areItemsTheSame(oldItem: RecipeReview, newItem: RecipeReview): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipeReview, newItem: RecipeReview): Boolean {
        return oldItem.id == newItem.id
    }
}