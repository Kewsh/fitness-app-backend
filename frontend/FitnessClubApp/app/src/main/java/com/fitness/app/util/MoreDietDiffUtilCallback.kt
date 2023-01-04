package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.MoreDiet

class MoreDietDiffUtilCallback: DiffUtil.ItemCallback<MoreDiet>() {
    override fun areItemsTheSame(oldItem: MoreDiet, newItem: MoreDiet): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: MoreDiet, newItem: MoreDiet): Boolean {
        return oldItem.title == newItem.title
    }
}