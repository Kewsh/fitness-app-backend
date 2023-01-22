package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.Food

class DietDiffUtilCallback : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.image == newItem.image
    }
}