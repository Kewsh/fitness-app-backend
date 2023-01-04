package com.fitness.app.util

import androidx.recyclerview.widget.DiffUtil
import com.fitness.app.model.UserComment

class UserCommentDiffUtilCallback : DiffUtil.ItemCallback<UserComment>()  {
    override fun areItemsTheSame(oldItem: UserComment, newItem: UserComment): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: UserComment, newItem: UserComment): Boolean {
        return oldItem.name == newItem.name
    }
}