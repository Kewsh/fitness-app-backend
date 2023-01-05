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
import com.fitness.app.databinding.WhoTriedFoodItemBinding
import com.fitness.app.model.WhoTriedFood
import com.fitness.app.util.WhoTriedFoodDiffUtilCallback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class WhoTriedFoodAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) : ListAdapter<WhoTriedFood, WhoTriedFoodAdapter.WhoTriedFoodViewHolder>(
    WhoTriedFoodDiffUtilCallback()
) {

    inner class WhoTriedFoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: WhoTriedFoodItemBinding = WhoTriedFoodItemBinding.bind(itemView)
        val foodImage: ImageView = binding.foodImage
        val name: TextView = binding.name
        val rating: TextView = binding.rating
        val description: TextView = binding.description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhoTriedFoodViewHolder {
        return WhoTriedFoodViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.who_tried_food_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WhoTriedFoodViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { whoTriedFood ->
            holder.apply {
                Picasso.get().load(whoTriedFood.foodImage).memoryPolicy(
                    MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).into(foodImage)
                name.text = whoTriedFood.name
                rating.text = whoTriedFood.rating
                description.text = whoTriedFood.description
            }

        }

    }
}