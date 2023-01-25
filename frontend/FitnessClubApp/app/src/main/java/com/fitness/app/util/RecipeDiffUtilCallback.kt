package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.Recipe

class RecipeDiffUtilCallback : DiffUtil.ItemCallback<Recipe>()  {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.image == newItem.image
    }
}