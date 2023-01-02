package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.DiscoverProgram

class DiscoverProgramDiffUtilCallback: DiffUtil.ItemCallback<DiscoverProgram>() {
    override fun areItemsTheSame(oldItem: DiscoverProgram, newItem: DiscoverProgram): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: DiscoverProgram, newItem: DiscoverProgram): Boolean {
        return oldItem.title == newItem.title
    }
}