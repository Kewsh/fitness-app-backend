package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.DietPlan

class DietPlanDiffUtilCallback: DiffUtil.ItemCallback<DietPlan>() {
    override fun areItemsTheSame(oldItem: DietPlan, newItem: DietPlan): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: DietPlan, newItem: DietPlan): Boolean {
        return oldItem.title == newItem.title
    }
}