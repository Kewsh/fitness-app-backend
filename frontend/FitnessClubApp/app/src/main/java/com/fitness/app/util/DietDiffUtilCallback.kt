package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.Diet

class DietDiffUtilCallback : DiffUtil.ItemCallback<Diet>() {
    override fun areItemsTheSame(oldItem: Diet, newItem: Diet): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: Diet, newItem: Diet): Boolean {
        return oldItem.image == newItem.image
    }
}