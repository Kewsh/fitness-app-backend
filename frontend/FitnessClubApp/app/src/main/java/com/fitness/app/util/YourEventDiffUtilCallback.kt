package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.Event

class YourEventDiffUtilCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }
}