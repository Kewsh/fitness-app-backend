package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.YourEvent

class YourEventDiffUtilCallback : DiffUtil.ItemCallback<YourEvent>() {
    override fun areItemsTheSame(oldItem: YourEvent, newItem: YourEvent): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: YourEvent, newItem: YourEvent): Boolean {
        return oldItem.image == newItem.image
    }
}