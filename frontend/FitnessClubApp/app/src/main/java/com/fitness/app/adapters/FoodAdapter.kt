package com.fitness.app.adapters

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fitness.app.R
import com.fitness.app.databinding.AthleteFoodItemBinding
import com.fitness.app.model.Food
import com.fitness.app.util.FoodDiffUtilCallback
import com.fitness.app.util.RecipeDiffUtilCallback
import com.fitness.app.views.fragments.AthleteDietDescriptionFragment

class FoodAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
) : ListAdapter<Food, FoodAdapter.DietViewHolder>(FoodDiffUtilCallback()) {

    inner class DietViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: AthleteFoodItemBinding = AthleteFoodItemBinding.bind(itemView)
        val image: ImageView = binding.foodImage
        val title: TextView = binding.foodTitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietViewHolder {
        return DietViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.athlete_food_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DietViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        getItem(position).let { food ->
            holder.apply {
                image.background = BitmapDrawable(context.resources, food.image)
                title.text = food.title
            }

        }

    }

}