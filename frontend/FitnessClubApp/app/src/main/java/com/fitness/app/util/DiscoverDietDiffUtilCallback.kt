package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.DiscoverDiet

class DiscoverDietDiffUtilCallback: DiffUtil.ItemCallback<DiscoverDiet>() {
    override fun areItemsTheSame(oldItem: DiscoverDiet, newItem: DiscoverDiet): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: DiscoverDiet, newItem: DiscoverDiet): Boolean {
        return oldItem.title == newItem.title
    }
}