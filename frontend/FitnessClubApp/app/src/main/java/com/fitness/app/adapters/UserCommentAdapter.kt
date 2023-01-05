package com.fitness.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.UserCommentsItemBinding
import com.fitness.app.model.UserComment
import com.fitness.app.util.UserCommentDiffUtilCallback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class UserCommentAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) : ListAdapter<UserComment, UserCommentAdapter.UserCommentViewHolder>(UserCommentDiffUtilCallback())  {

    inner class UserCommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: UserCommentsItemBinding = UserCommentsItemBinding.bind(itemView)
        val profileImage: CircleImageView = binding.profilePic
        val name: TextView = binding.name
        val rating: TextView = binding.rating
        val description: TextView = binding.description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCommentViewHolder {
        return UserCommentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_comments_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserCommentViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { userComment ->
            holder.apply {
                Picasso.get().load(userComment.profileImage).memoryPolicy(
                    MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(profileImage)
                name.text = userComment.name
                rating.text = userComment.rating
                description.text = userComment.description
            }

        }

    }
}