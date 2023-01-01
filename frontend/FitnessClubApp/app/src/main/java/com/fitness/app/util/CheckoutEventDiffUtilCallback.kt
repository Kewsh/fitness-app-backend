package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.CheckoutEvent

class CheckoutEventDiffUtilCallback : DiffUtil.ItemCallback<CheckoutEvent>()  {
    override fun areItemsTheSame(oldItem: CheckoutEvent, newItem: CheckoutEvent): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: CheckoutEvent, newItem: CheckoutEvent): Boolean {
        return oldItem.image == newItem.image
    }
}