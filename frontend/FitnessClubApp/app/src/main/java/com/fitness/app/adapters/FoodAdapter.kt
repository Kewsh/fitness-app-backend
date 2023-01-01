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
import com.fitness.app.databinding.AthleteFoodItemBinding
import com.fitness.app.databinding.AthleteTodayDietItemBinding
import com.fitness.app.model.Food
import com.fitness.app.util.FoodDiffUtilCallback

class FoodAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
): ListAdapter<Food, FoodAdapter.FoodViewHolder>(FoodDiffUtilCallback()) {

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteFoodItemBinding = AthleteFoodItemBinding.bind(itemView)
        val image: ImageView = binding.foodImage
        val title: TextView = binding.foodTitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_food_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { diet ->
            holder.apply {
                image.setBackgroundResource(diet.image)
                title.text = diet.title
            }

        }

    }
}