package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.DiscoverEvent

class DiscoverEventDiffUtilCallback: DiffUtil.ItemCallback<DiscoverEvent>() {
    override fun areItemsTheSame(oldItem: DiscoverEvent, newItem: DiscoverEvent): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: DiscoverEvent, newItem: DiscoverEvent): Boolean {
        return oldItem.title == newItem.title
    }
}