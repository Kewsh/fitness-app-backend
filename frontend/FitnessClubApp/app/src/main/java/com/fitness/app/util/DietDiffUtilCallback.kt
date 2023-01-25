package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.Diet
import com.fitness.app.model.Food

class DietDiffUtilCallback : DiffUtil.ItemCallback<Diet>() {
    override fun areItemsTheSame(oldItem: Diet, newItem: Diet): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Diet, newItem: Diet): Boolean {
        return oldItem.id == newItem.id
    }
}