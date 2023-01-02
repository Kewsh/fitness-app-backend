package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.DayWorkout

class DayWorkoutDiffUtilCallback: DiffUtil.ItemCallback<DayWorkout>() {
    override fun areItemsTheSame(oldItem: DayWorkout, newItem: DayWorkout): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: DayWorkout, newItem: DayWorkout): Boolean {
        return oldItem.title == newItem.title
    }
}