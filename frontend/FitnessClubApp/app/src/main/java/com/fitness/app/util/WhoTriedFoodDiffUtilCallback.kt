package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.WhoTriedFood

class WhoTriedFoodDiffUtilCallback : DiffUtil.ItemCallback<WhoTriedFood>() {
    override fun areItemsTheSame(oldItem: WhoTriedFood, newItem: WhoTriedFood): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: WhoTriedFood, newItem: WhoTriedFood): Boolean {
        return oldItem.name == newItem.name
    }
}